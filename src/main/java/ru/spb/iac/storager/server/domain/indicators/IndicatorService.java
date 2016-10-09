package ru.spb.iac.storager.server.domain.indicators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.domain.shared.PagedResult;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemInfo;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemMapper;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemRepository;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemService;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemValidator;

@Service
@Transactional
public class IndicatorService extends HierarchicItemService<Indicator> {

    @Autowired
    private IndicatorMapper mapper;

    @Autowired
    private IndicatorRepository repository;

    @Autowired
    private IndicatorValidator validator;

    public PagedResult<HierarchicItemInfo> getPage(final String codePattern,
                                                   final String ascendantCode,
                                                   final String titlePattern,
                                                   final int page,
                                                   final int size) {
        throw new UnsupportedOperationException("service method is not implemented yet");
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
}
