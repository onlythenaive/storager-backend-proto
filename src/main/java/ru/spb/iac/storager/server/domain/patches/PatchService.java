package ru.spb.iac.storager.server.domain.patches;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.domain.providers.Provider;
import ru.spb.iac.storager.server.domain.providers.ProviderRepository;
import ru.spb.iac.storager.server.domain.shared.PagedResult;
import ru.spb.iac.storager.server.errors.domain.InputValidationHelper;
import ru.spb.iac.storager.server.errors.domain.ItemValidationHelper;
import ru.spb.iac.storager.server.errors.domain.MissingItemException;
import ru.spb.iac.storager.server.errors.shared.ReasonableException;
import ru.spb.iac.storager.server.security.ProviderAuthentication;
import ru.spb.iac.storager.server.security.SecurityContext;
import static ru.spb.iac.storager.server.security.SecurityRoles.ADMIN;
import static ru.spb.iac.storager.server.security.SecurityRoles.USER;

@Service
public class PatchService {

    @Autowired
    private SecurityContext securityContext;

    @Autowired
    private InputValidationHelper inputValidationHelper;

    @Autowired
    private ItemValidationHelper itemValidationHelper;

    @Autowired
    private PatchRepository repository;

    @Autowired
    private PatchMapper mapper;

    @Autowired
    private PatchCreationService patchCreationService;

    @Autowired
    private ProviderRepository providerRepository;

    @Transactional
    public PatchInfo bootstrap(final String providerTitle, final PatchProperties properties) {
        final Provider provider = providerRepository.findByTitle(providerTitle);
        return patchCreationService.createWithSuccess(provider.getId(), properties);
    }

    @Transactional
    public PatchInfo createOnProviderBehalf(final PatchProperties properties) {
        final ProviderAuthentication authentication = securityContext.providerAuthenticated();
        try {
            return patchCreationService.createWithSuccess(authentication.getId(), properties);
        } catch (final Exception exception) {
            final String failureReason = getFailureReason(exception);
            final PatchInfo info = patchCreationService.createWithFailure(authentication.getId(), properties, failureReason);
            throw new PatchCreationException(info);
        }
    }

    @Transactional
    public PatchInfo createInSandboxOnProviderBehalf(final PatchProperties properties) {
        final ProviderAuthentication authentication = securityContext.providerAuthenticated();
        try {
            patchCreationService.createAndRollbackWithSuccess(authentication.getId(), properties);
        } catch (final PatchSandboxCreationException exception) {
            return exception.getInfo();
        } catch (final Exception exception) {
            try {
                final String failureReason = getFailureReason(exception);
                patchCreationService.createAndRollbackWithFailure(authentication.getId(), properties, failureReason);
            } catch (final PatchSandboxCreationException e) {
                throw new PatchCreationException(e.getInfo());
            } catch (final Exception e) {
                throw new RuntimeException("unknown error occurred while creating a new patch");
            }
        }
        throw new RuntimeException("should never executed");
    }

    @Transactional(readOnly = true)
    public PatchInfo getByIdOnProviderBehalf(final Integer id) {
        final ProviderAuthentication authentication = securityContext.providerAuthenticated();
        final Patch patch = get(id);
        if (!patch.getProvider().getId().equals(authentication.getId())) {
            throw new MissingItemException("id", id);
        }
        return mapper.intoInfo(patch);
    }

    @Transactional(readOnly = true)
    public PatchInfo getByIdOnUserBehalf(final Integer id) {
        securityContext.userAuthorizedWithAny(USER);
        return mapper.intoInfo(get(id));
    }

    @Transactional(readOnly = true)
    public PagedResult<PatchInfo> getPageOnProviderBehalf(final String status,
                                                          final String createdSince,
                                                          final String createdUntil,
                                                          final int page,
                                                          final int size) {
        final ProviderAuthentication authentication = securityContext.providerAuthenticated();
        inputValidationHelper.positive(page, "page");
        inputValidationHelper.positive(size, "size");
        Page<Patch> patchPage = repository.findPageWithFilter(
                authentication.getId(),
                defaultedStatus(status),
                defaultedCreatedSince(createdSince),
                defaultedCreatedUntil(createdUntil),
                new PageRequest(page - 1, size)
        );
        List<PatchInfo> infos = patchPage
                .getContent()
                .stream()
                .map(mapper::intoInfo)
                .collect(Collectors.toList());
        return new PagedResult<>(infos, page, patchPage.getTotalPages());
    }

    @Transactional(readOnly = true)
    public PagedResult<PatchInfo> getPageOnUserBehalf(final String providerTitlePattern,
                                                      final String status,
                                                      final String createdSince,
                                                      final String createdUntil,
                                                      final int page,
                                                      final int size) {
        securityContext.userAuthorizedWithAny(ADMIN, USER);
        inputValidationHelper.positive(page, "page");
        inputValidationHelper.positive(size, "size");
        Page<Patch> patchPage = repository.findPageWithFilter(
                defaultedProviderTitlePattern(providerTitlePattern),
                defaultedStatus(status),
                defaultedCreatedSince(createdSince),
                defaultedCreatedUntil(createdUntil),
                new PageRequest(page - 1, size)
        );
        List<PatchInfo> infos = patchPage
                .getContent()
                .stream()
                .map(mapper::intoInfo)
                .collect(Collectors.toList());
        return new PagedResult<>(infos, page, patchPage.getTotalPages());
    }

    private Instant defaultedCreatedSince(final String createdSince) {
        return isNotEmpty(createdSince) ?  Instant.parse(createdSince) : Instant.ofEpochMilli(0);
    }

    private Instant defaultedCreatedUntil(final String createdUntil) {
        return isNotEmpty(createdUntil) ? Instant.parse(createdUntil) : Instant.now();
    }

    private String defaultedProviderTitlePattern(final String providerTitle) {
        return isNotEmpty(providerTitle) ? "%" + providerTitle + "%" : "%";
    }

    private String defaultedStatus(final String status) {
        return isNotEmpty(status) ? status : "%";
    }

    private Patch get(final Integer id) {
        inputValidationHelper.required(id, "id");
        return itemValidationHelper.required(repository.findOne(id), "id", id);
    }

    private boolean isNotEmpty(final String string) {
        return !isNullOrEmpty(string);
    }

    private boolean isNullOrEmpty(final String string) {
        return string == null || string.isEmpty();
    }

    private String getFailureReason(final Exception exception) {
        if (exception instanceof ReasonableException) {
            return ((ReasonableException) exception).getMessage();
        }
        return "unknown error";
    }
}
