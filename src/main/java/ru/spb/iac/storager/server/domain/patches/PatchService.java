package ru.spb.iac.storager.server.domain.patches;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.domain.shared.PagedResult;
import ru.spb.iac.storager.server.errors.domain.InputValidationHelper;
import ru.spb.iac.storager.server.errors.domain.ItemValidationHelper;

@Service
@Transactional
public class PatchService {

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

    public PatchInfo create(final PatchProperties properties) {
        try {
            return patchCreationService.createWithSuccess(properties);
        } catch (final Exception exception) {
            throw new PatchCreationException(patchCreationService.createWithFailure(properties));
        }
    }

    public void createInSandbox(final PatchProperties properties) throws PatchRollbackException {
        try {
            patchCreationService.createAndRollbackWithSuccess(properties);
        } catch (final PatchRollbackException exception) {
            throw exception;
        } catch (final Exception exception) {
            try {
                patchCreationService.createAndRollbackWithFailure(properties);
            } catch (final PatchRollbackException e) {
                throw new PatchCreationException(e.getInfo());
            }
        }
    }

    public PatchInfo getById(final Integer id) {
        return mapper.intoInfo(get(id));
    }

    public PagedResult<PatchInfo> getPage(final String providerTitle,
                                          final String status,
                                          final String createdSince,
                                          final String createdUntil,
                                          final int page,
                                          final int size) {
        inputValidationHelper.positive(page, "page");
        inputValidationHelper.positive(size, "size");
        Page<Patch> patchPage = repository.findPageWithFilter(
                defaultedProviderTitle(providerTitle),
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

    private String defaultedProviderTitle(final String providerTitle) {
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
