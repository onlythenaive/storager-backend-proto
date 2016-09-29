package ru.spb.iac.storager.server.domain.providers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.domain.grants.Grant;
import ru.spb.iac.storager.server.domain.indicators.Indicator;
import ru.spb.iac.storager.server.domain.indicators.IndicatorRepository;
import ru.spb.iac.storager.server.domain.shared.PagedResult;

@Service
@Transactional
public class ProviderService {

    @Autowired
    private IndicatorRepository indicatorRepository;

    @Autowired
    private ProviderRepository providerRepository;

    public ProviderInfo create(ProviderInfo info) {
        return ProviderInfo.fromProvider(providerRepository.save(Provider.of(info.getTitle(), info.getDescription(), UUID.randomUUID().toString())));
    }

    public PagedResult<ProviderInfo> getPage(Integer page, Integer size) {
        Page<Provider> providerPage = providerRepository.findAll(new PageRequest(page - 1, size));
        List<ProviderInfo> infos = providerPage
                .getContent()
                .stream()
                .map(ProviderInfo::fromProvider)
                .collect(Collectors.toList());
        return new PagedResult<>(infos, page, providerPage.getTotalPages());
    }

    public ProviderInfo getById(Integer id) {
        return ProviderInfo.fromProvider(providerRepository.findOne(id));
    }

    public void remove(Integer id) {
        providerRepository.delete(id);
    }

    public ProviderInfo update(Integer id, ProviderInfo info) {
        Provider provider = providerRepository.findOne(id);
        provider.setTitle(info.getTitle());
        provider.setDescription(info.getDescription());
        return ProviderInfo.fromProvider(providerRepository.save(provider));
    }

    public ProviderInfo updateGrants(Integer id, List<String> indicatorCodes) {
        Provider provider = providerRepository.findOne(id);
        for (String code : indicatorCodes) {
            Indicator indicator = indicatorRepository.findByCode(code);
            Grant grant = Grant.of(indicator, provider);
            provider.getGrants().add(grant);
        }
        return ProviderInfo.fromProvider(providerRepository.save(provider));
    }

    public ProviderInfo updateToken(Integer id) {
        Provider provider = providerRepository.findOne(id);
        provider.setToken(UUID.randomUUID().toString());
        return ProviderInfo.fromProvider(providerRepository.save(provider));
    }
}
