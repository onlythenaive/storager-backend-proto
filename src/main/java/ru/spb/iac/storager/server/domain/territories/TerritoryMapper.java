package ru.spb.iac.storager.server.domain.territories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemMapper;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemRepository;

@Service
public class TerritoryMapper extends HierarchicItemMapper<Territory> {

    @Autowired
    private TerritoryRepository repository;

    @Override
    protected HierarchicItemRepository<Territory> getRepository() {
        return repository;
    }

    @Override
    protected Territory createItemInstance() {
        return new Territory();
    }
}
