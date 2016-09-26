package ru.spb.iac.storager.server.data.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    public ProviderInfo create(ProviderInfo info) {
        return ProviderInfo.fromProvider(providerRepository.save(new Provider(info.getTitle(), info.getDescription())));
    }

    public List<ProviderInfo> getAll() {
        return providerRepository
                .findAll()
                .stream()
                .map(ProviderInfo::fromProvider)
                .collect(Collectors.toList());
    }
}
