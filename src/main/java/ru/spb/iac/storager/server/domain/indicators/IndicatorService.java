package ru.spb.iac.storager.server.domain.indicators;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.domain.grants.Grant;
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
                defaultedHierarchicType(ascendantCode),
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
        final Set<String> implicitGrants = new HashSet<>();
        providerRepository
                .findOne(providerId)
                .getGrants()
                .stream()
                .map(Grant::getIndicator)
                .forEach(entity -> addToImplicitGrants(entity, implicitGrants));
        return implicitGrants;
    }

    private void addToImplicitGrants(final Indicator entity, final Set<String> implicitGrants) {
        implicitGrants.add(entity.getCode());
        entity.getDescendants().forEach(d -> addToImplicitGrants(d, implicitGrants));
    }

    private String defaultedCodePattern(final String codePattern) {
        return codePattern == null || codePattern.isEmpty() ? "%" : codePattern + "%";
    }

    private String defaultedAscendantCode(final String ascendantCode) {
        return ascendantCode == null || ascendantCode.isEmpty() ? "%" : ascendantCode;
    }

    private String defaultedHierarchicType(final String ascendantCode) {
        if (ascendantCode == null || ascendantCode.isEmpty()) {
            return "ANY";
        }
        if (ascendantCode.equals("NIL")) {
            return "ROOT";
        }
        return "DESCENDANT";
    }

    private String defaultedTitlePattern(final String titlePattern) {
        return titlePattern == null || titlePattern.isEmpty() ? "%" :  "%" + titlePattern + "%";
    }
}
