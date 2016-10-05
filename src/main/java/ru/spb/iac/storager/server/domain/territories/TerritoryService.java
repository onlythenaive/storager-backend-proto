package ru.spb.iac.storager.server.domain.territories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemMapper;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemRepository;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemService;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemValidator;

@Service
@Transactional
public class TerritoryService extends HierarchicItemService<Territory> {

    @Autowired
    private TerritoryMapper mapper;

    @Autowired
    private TerritoryRepository repository;

    @Autowired
    private TerritoryValidator validator;

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
}
