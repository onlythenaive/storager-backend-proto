package ru.spb.iac.storager.server.domain.providers;

import java.util.List;
import java.util.Set;
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
import ru.spb.iac.storager.server.errors.domain.InputValidationHelper;
import ru.spb.iac.storager.server.errors.domain.ItemValidationHelper;

@Service
@Transactional
public class ProviderService {

    @Autowired
    private InputValidationHelper inputValidationHelper;

    @Autowired
    private ItemValidationHelper itemValidationHelper;

    @Autowired
    private IndicatorRepository indicatorRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ProviderMapper mapper;

    @Autowired
    private ProviderValidator validator;

    public ProviderInfo create(final ProviderProperties properties) {
        final Provider entity = mapper.intoEntity(validator.validateForCreate(properties));
        entity.setToken(generateToken());
        return mapper.intoInfo(providerRepository.save(entity));
    }

    public PagedResult<ProviderInfo> getPage(final int page, final int size) {
        inputValidationHelper.positive(page, "page");
        inputValidationHelper.positive(size, "size");
        final Page<Provider> providerPage = providerRepository.findAll(new PageRequest(page - 1, size));
        final List<ProviderInfo> infos = providerPage
                .getContent()
                .stream()
                .map(mapper::intoInfo)
                .collect(Collectors.toList());
        return new PagedResult<>(infos, page, providerPage.getTotalPages());
    }

    public ProviderInfo getById(final Integer id) {
        return mapper.intoInfo(get(id));
    }

    public ProviderTokenInfo getTokenInfoById(final Integer id) {
        return mapper.intoTokenInfo(get(id));
    }

    public void remove(final Integer id) {
        providerRepository.delete(get(id));
    }

    public ProviderInfo update(final Integer id, final ProviderProperties properties) {
        final Provider entity = get(id);
        mapper.intoEntity(validator.validateForUpdate(id, properties), entity);
        return mapper.intoInfo(providerRepository.save(entity));
    }

    public ProviderInfo updateGrants(final Integer id, final Set<String> indicatorCodes) {
        final Provider provider = get(id);
        provider.getGrants().clear();
        for (final String code : indicatorCodes) {
            final Indicator indicator = getIndicator(code);
            final Grant grant = new Grant(indicator, provider);
            provider.getGrants().add(grant);
        }
        return mapper.intoInfo(providerRepository.save(provider));
    }

    public ProviderInfo updateToken(final Integer id) {
        final Provider provider = get(id);
        provider.setToken(generateToken());
        return mapper.intoInfo(providerRepository.save(provider));
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }

    private Provider get(final Integer id) {
        inputValidationHelper.required(id, "id");
        return itemValidationHelper.required(providerRepository.findOne(id), "id", id);
    }

    private Indicator getIndicator(final String code) {
        return itemValidationHelper.required(indicatorRepository.findByCode(code), "code", code);
    }
}
