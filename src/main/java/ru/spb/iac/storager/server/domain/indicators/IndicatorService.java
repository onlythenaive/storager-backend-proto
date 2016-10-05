package ru.spb.iac.storager.server.domain.indicators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
