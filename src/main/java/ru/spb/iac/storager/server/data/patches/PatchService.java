package ru.spb.iac.storager.server.data.patches;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spb.iac.storager.server.data.providers.ProviderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PatchService {

    @Autowired
    private PatchRepository patchRepository;

    @Autowired
    private ProviderRepository providerRepository;

    public PatchInfo create(PatchInfo info) {
        Patch patch = new Patch();
        patch.setComment(info.getComment());
        patch.setProvider(providerRepository.findOne(info.getProviderId()));
        return PatchInfo.fromPatch(patchRepository.save(patch));
    }

    public List<PatchInfo> getAll() {
        return patchRepository.
                findAll()
                .stream()
                .map(PatchInfo::fromPatch)
                .collect(Collectors.toList());
    }

    public PatchInfo getById(Integer id) {
        return PatchInfo.fromPatch(patchRepository.findOne(id));
    }
}
