package ru.spb.iac.storager.server.domain.indicators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemRepository;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemValidatorGeneric;
import ru.spb.iac.storager.server.errors.domain.InputValidationHelper;

@Service
@Transactional(readOnly = true)
public class IndicatorValidator extends HierarchicItemValidatorGeneric<Indicator> {

    @Autowired
    private InputValidationHelper inputValidationHelper;

    @Autowired
    private IndicatorRepository repository;

    @Override
    protected InputValidationHelper getInputValidationHelper() {
        return inputValidationHelper;
    }

    @Override
    protected HierarchicItemRepository<Indicator> getRepository() {
        return repository;
    }
}
