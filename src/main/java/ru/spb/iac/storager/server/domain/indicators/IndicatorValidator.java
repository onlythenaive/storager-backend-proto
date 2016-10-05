package ru.spb.iac.storager.server.domain.indicators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemRepository;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemValidator;

@Service
@Transactional(readOnly = true)
public class IndicatorValidator extends HierarchicItemValidator<Indicator> {

    @Autowired
    private IndicatorRepository repository;

    @Override
    protected HierarchicItemRepository<Indicator> getRepository() {
        return repository;
    }
}
