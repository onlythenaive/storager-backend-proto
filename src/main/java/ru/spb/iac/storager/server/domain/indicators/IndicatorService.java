package ru.spb.iac.storager.server.domain.indicators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemMapper;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemRepository;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemServiceGeneric;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemValidator;
import ru.spb.iac.storager.server.errors.domain.InputValidationHelper;
import ru.spb.iac.storager.server.errors.domain.ItemValidationHelper;

@Service
@Transactional
public class IndicatorService extends HierarchicItemServiceGeneric<Indicator> {

    @Autowired
    private InputValidationHelper inputValidationMapper;

    @Autowired
    private ItemValidationHelper itemValidationHelper;

    @Autowired
    private IndicatorMapper mapper;

    @Autowired
    private IndicatorRepository repository;

    @Autowired
    private IndicatorValidator validator;

    @Override
    protected InputValidationHelper getInputValidationHelper() {
        return inputValidationMapper;
    }

    @Override
    protected ItemValidationHelper getItemValidationHelper() {
        return itemValidationHelper;
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
