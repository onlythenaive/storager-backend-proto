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

@Service
@Transactional
public class PatchService {

    @Autowired
    private PatchRepository patchRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private PatchCreationService patchCreationService;

    public PatchInfo create(PatchInvoice invoice) {
        try {
            return patchCreationService.create(invoice);
        } catch (Exception exception) {
            exception.printStackTrace();
            String comment = invoice.getComment();
            Provider provider = providerRepository.findByToken(invoice.getProviderToken());
            Patch failedPatch = new Patch(comment, "FAILED", provider);
            return PatchInfo.fromPatch(patchRepository.save(failedPatch));
        }
    }

    public PatchInfo getById(Integer id) {
        return PatchInfo.fromPatch(patchRepository.findOne(id));
    }

    public PagedResult<PatchInfo> getPage(String providerTitle, String status, String createdSince, String createdUntil, int page, int size) {
        Page<Patch> patchPage = patchRepository.findPageWithFilter(
                defaultedProviderTitle(providerTitle),
                defaultedStatus(status),
                defaultedCreatedSince(createdSince),
                defaultedCreatedUntil(createdUntil),
                new PageRequest(page - 1, size)
        );
        List<PatchInfo> infos = patchPage
                .getContent()
                .stream()
                .map(PatchInfo::fromPatch)
                .collect(Collectors.toList());
        return new PagedResult<>(infos, page, patchPage.getTotalPages());
    }

    private Instant defaultedCreatedSince(String createdSince) {
        return createdSince != null ? Instant.parse(createdSince) : Instant.ofEpochMilli(0);
    }

    private Instant defaultedCreatedUntil(String createdUntil) {
        return createdUntil != null ? Instant.parse(createdUntil) : Instant.now();
    }

    private String defaultedProviderTitle(String providerTitle) {
        return providerTitle != null ? providerTitle : "%";
    }

    private String defaultedStatus(String status) {
        return status != null ? status : "%";
    }
}
