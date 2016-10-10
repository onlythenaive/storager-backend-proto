package ru.spb.iac.storager.server.domain.indicators;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.domain.providers.ProviderRepository;
import ru.spb.iac.storager.server.domain.shared.PagedResult;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemInfo;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemMapper;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemRepository;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemService;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemValidator;
import ru.spb.iac.storager.server.errors.domain.InputValidationHelper;
import ru.spb.iac.storager.server.errors.domain.MissingItemException;
import ru.spb.iac.storager.server.security.ProviderAuthentication;
import ru.spb.iac.storager.server.security.SecurityContext;

@Service
public class IndicatorService extends HierarchicItemService<Indicator> {

    @Autowired
    private SecurityContext securityContext;

    @Autowired
    private InputValidationHelper inputValidationHelper;

    @Autowired
    private IndicatorMapper mapper;

    @Autowired
    private IndicatorRepository repository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private IndicatorValidator validator;

    @Transactional(readOnly = true)
    public HierarchicItemInfo getByCodeOnProviderBehalf(final String code) {
        final ProviderAuthentication authentication = securityContext.providerAuthenticated();
        final Set<String> implicitGrants = getImplicitGrants(authentication.getId());
        final Indicator entity = get(code);
        if (!implicitGrants.contains(entity.getCode())) {
            throw new MissingItemException("code", code);
        }
        return mapper.intoInfoWithGrantsApplied(entity, implicitGrants);
    }

    @Transactional(readOnly = true)
    public PagedResult<HierarchicItemInfo> getPageOnProviderBehalf(final String codePattern,
                                                                   final String ascendantCode,
                                                                   final String titlePattern,
                                                                   final int page,
                                                                   final int size) {
        final ProviderAuthentication authentication = securityContext.providerAuthenticated();
        final Set<String> implicitGrants = getImplicitGrants(authentication.getId());
        inputValidationHelper.positive(page, "page");
        inputValidationHelper.positive(size, "size");
        Page<Indicator> indicatorPage = repository.findPageWithFilter(
                defaultedCodePattern(codePattern),
                defaultedAscendantCode(ascendantCode),
                defaultedRoot(ascendantCode),
                defaultedTitlePattern(titlePattern),
                implicitGrants,
                new PageRequest(page - 1, size)
        );
        List<HierarchicItemInfo> infos = indicatorPage
                .getContent()
                .stream()
                .map(i -> mapper.intoInfoWithGrantsApplied(i, implicitGrants))
                .collect(Collectors.toList());
        return new PagedResult<>(infos, page, indicatorPage.getTotalPages());
    }

    @Override
    protected HierarchicItemMapper<Indicator> getMapper() {
        return mapper;
    }

    @Override
    protected HierarchicItemRepository<Indicator> getRepository() {
        return repository;
    }

    @Override
    protected HierarchicItemValidator getValidator() {
        return validator;
    }

    private Set<String> getImplicitGrants(final Integer providerId) {
        return providerRepository
                .findOne(providerId)
                .getGrants()
                .stream()
                .map(g -> g.getIndicator().getCode())
                .collect(Collectors.toSet());
    }

    private String defaultedCodePattern(final String codePattern) {
        return codePattern != null ? codePattern : "%";
    }

    private String defaultedAscendantCode(final String ascendantCode) {
        return ascendantCode != null ? ascendantCode : "%";
    }

    private int defaultedRoot(final String ascendantCode) {
        return "NIL".equals(ascendantCode) ? 1 : 0;
    }

    private String defaultedTitlePattern(final String titlePattern) {
        return titlePattern != null ? titlePattern : "%";
    }
}
