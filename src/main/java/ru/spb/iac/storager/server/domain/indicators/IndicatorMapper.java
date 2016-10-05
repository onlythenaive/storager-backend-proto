package ru.spb.iac.storager.server.domain.indicators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemMapper;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemRepository;

@Service
@Transactional(readOnly = true)
public class IndicatorMapper extends HierarchicItemMapper<Indicator> {

    @Autowired
    private IndicatorRepository repository;

    @Override
    protected HierarchicItemRepository<Indicator> getRepository() {
        return repository;
    }

    @Override
    protected Indicator createItemInstance() {
        return new Indicator();
    }
}
