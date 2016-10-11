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
import ru.spb.iac.storager.server.security.ProviderAuthentication;
import ru.spb.iac.storager.server.security.SecurityContext;
import static ru.spb.iac.storager.server.security.SecurityRoles.ADMIN;
import static ru.spb.iac.storager.server.security.SecurityRoles.USER;

@Service
@Transactional
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

    public PatchInfo bootstrap(final String providerTitle, final PatchProperties properties) {
        final Provider provider = providerRepository.findByTitle(providerTitle);
        return patchCreationService.createWithSuccess(provider.getId(), properties);
    }

    public PatchInfo createOnProviderBehalf(final PatchProperties properties) {
        final ProviderAuthentication authentication = securityContext.providerAuthenticated();
        try {
            return patchCreationService.createWithSuccess(authentication.getId(), properties);
        } catch (final Exception exception) {
            throw new PatchCreationException(patchCreationService.createWithFailure(authentication.getId(), properties));
        }
    }

    public void createInSandboxOnProviderBehalf(final PatchProperties properties) throws PatchRollbackException {
        final ProviderAuthentication authentication = securityContext.providerAuthenticated();
        try {
            patchCreationService.createAndRollbackWithSuccess(authentication.getId(), properties);
        } catch (final PatchRollbackException exception) {
            throw exception;
        } catch (final Exception exception) {
            try {
                patchCreationService.createAndRollbackWithFailure(authentication.getId(), properties);
            } catch (final PatchRollbackException e) {
                throw new PatchCreationException(e.getInfo());
            }
        }
    }

    public PatchInfo getByIdOnProviderBehalf(final Integer id) {
        final ProviderAuthentication authentication = securityContext.providerAuthenticated();
        // TODO: implement this method
        throw new UnsupportedOperationException();
    }

    public PatchInfo getByIdOnUserBehalf(final Integer id) {
        securityContext.userAuthorizedWithAny(USER);
        return mapper.intoInfo(get(id));
    }

    public PagedResult<PatchInfo> getPageOnProviderBehalf(final String providerTitlePattern,
                                                          final String status,
                                                          final String createdSince,
                                                          final String createdUntil,
                                                          final int page,
                                                          final int size) {
        final ProviderAuthentication authentication = securityContext.providerAuthenticated();
        // TODO: implement this method
        throw new UnsupportedOperationException();
    }

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
        return createdSince != null ? Instant.parse(createdSince) : Instant.ofEpochMilli(0);
    }

    private Instant defaultedCreatedUntil(final String createdUntil) {
        return createdUntil != null ? Instant.parse(createdUntil) : Instant.now();
    }

    private String defaultedProviderTitlePattern(final String providerTitle) {
        return providerTitle != null ? providerTitle : "%";
    }

    private String defaultedStatus(final String status) {
        return status != null ? status : "%";
    }

    private Patch get(final Integer id) {
        inputValidationHelper.required(id, "id");
        return itemValidationHelper.required(repository.findOne(id), "id", id);
    }
}
