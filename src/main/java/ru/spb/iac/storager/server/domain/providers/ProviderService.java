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
import ru.spb.iac.storager.server.security.ProviderAuthentication;
import ru.spb.iac.storager.server.security.SecurityContext;

import static ru.spb.iac.storager.server.security.SecurityRoles.ADMIN;
import static ru.spb.iac.storager.server.security.SecurityRoles.USER;

@Service
public class ProviderService {

    @Autowired
    private SecurityContext securityContext;

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

    @Transactional
    public ProviderInfo bootstrap(final ProviderProperties properties) {
        return create(properties);
    }

    @Transactional
    public ProviderInfo bootstrapGrants(final Integer id, final Set<String> indicatorCodes) {
        return updateGrants(id, indicatorCodes);
    }

    @Transactional
    public ProviderInfo createByUser(final ProviderProperties properties) {
        securityContext.userAuthorizedWithAny(ADMIN);
        return create(properties);
    }

    @Transactional(readOnly = true)
    public ProviderInfo getAuthenticated() {
        final ProviderAuthentication authentication = securityContext.providerAuthenticated();
        return mapper.intoInfo(get(authentication.getId()));
    }

    @Transactional(readOnly = true)
    public ProviderInfo getById(final Integer id) {
        securityContext.userAuthorizedWithAny(ADMIN, USER);
        return mapper.intoInfo(get(id));
    }

    @Transactional(readOnly = true)
    public PagedResult<ProviderInfo> getPage(final int page, final int size) {
        securityContext.userAuthorizedWithAny(ADMIN, USER);
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

    public ProviderTokenInfo getTokenInfoById(final Integer id) {
        securityContext.userAuthorizedWithAny(ADMIN);
        return mapper.intoTokenInfo(get(id));
    }

    @Transactional
    public void remove(final Integer id) {
        securityContext.userAuthorizedWithAny(ADMIN);
        providerRepository.delete(get(id));
    }

    @Transactional
    public ProviderInfo update(final Integer id, final ProviderProperties properties) {
        securityContext.userAuthorizedWithAny(ADMIN);
        final Provider entity = get(id);
        mapper.intoEntity(validator.validateForUpdate(id, properties), entity);
        return mapper.intoInfo(providerRepository.save(entity));
    }

    @Transactional
    public ProviderInfo updateGrantsByUser(final Integer id, final Set<String> indicatorCodes) {
        securityContext.userAuthorizedWithAny(ADMIN);
        return updateGrants(id, indicatorCodes);
    }

    @Transactional
    public ProviderInfo updateToken(final Integer id) {
        securityContext.userAuthorizedWithAny(ADMIN);
        final Provider provider = get(id);
        provider.setToken(generateToken());
        return mapper.intoInfo(providerRepository.save(provider));
    }

    private ProviderInfo create(final ProviderProperties properties) {
        final Provider entity = mapper.intoEntity(validator.validateForCreate(properties));
        entity.setToken(generateToken());
        return mapper.intoInfo(providerRepository.save(entity));
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

    private ProviderInfo updateGrants(final Integer id, final Set<String> indicatorCodes) {
        final Provider provider = get(id);
        provider.getGrants().clear();
        for (final String code : indicatorCodes) {
            final Indicator indicator = getIndicator(code);
            final Grant grant = new Grant(indicator, provider);
            provider.getGrants().add(grant);
        }
        return mapper.intoInfo(providerRepository.save(provider));
    }
}
