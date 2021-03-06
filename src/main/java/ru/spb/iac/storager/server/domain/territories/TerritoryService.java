package ru.spb.iac.storager.server.domain.territories;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.domain.shared.PagedResult;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemInfo;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemMapper;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemRepository;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemService;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemValidator;
import ru.spb.iac.storager.server.errors.domain.InputValidationHelper;
import ru.spb.iac.storager.server.security.SecurityContext;

@Service
@Transactional
public class TerritoryService extends HierarchicItemService<Territory> {

    @Autowired
    private SecurityContext securityContext;

    @Autowired
    private InputValidationHelper inputValidationHelper;

    @Autowired
    private TerritoryMapper mapper;

    @Autowired
    private TerritoryRepository repository;

    @Autowired
    private TerritoryValidator validator;

    @Transactional(readOnly = true)
    public HierarchicItemInfo getByCodeOnProviderBehalf(final String code) {
        securityContext.providerAuthenticated();
        return getMapper().intoInfo(get(code));
    }

    @Transactional(readOnly = true)
    public PagedResult<HierarchicItemInfo> getPageOnProviderBehalf(final String codePattern,
                                                                   final String ascendantCode,
                                                                   final String titlePattern,
                                                                   final int page,
                                                                   final int size) {
        securityContext.providerAuthenticated();
        inputValidationHelper.positive(page, "page");
        inputValidationHelper.positive(size, "size");
        Page<Territory> territoryPage = repository.findPageWithFilter(
                defaultedCodePattern(codePattern),
                defaultedAscendantCode(ascendantCode),
                defaultedHierarchicType(ascendantCode),
                defaultedTitlePattern(titlePattern),
                new PageRequest(page - 1, size)
        );
        List<HierarchicItemInfo> infos = territoryPage
                .getContent()
                .stream()
                .map(mapper::intoInfo)
                .collect(Collectors.toList());
        return new PagedResult<>(infos, page, territoryPage.getTotalPages());
    }

    @Override
    protected HierarchicItemMapper<Territory> getMapper() {
        return mapper;
    }

    @Override
    protected HierarchicItemRepository<Territory> getRepository() {
        return repository;
    }

    @Override
    protected HierarchicItemValidator getValidator() {
        return validator;
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
