package ru.spb.iac.storager.server.domain.territories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemRepository;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemValidator;

@Service
public class TerritoryValidator extends HierarchicItemValidator<Territory> {

    @Autowired
    private TerritoryRepository repository;

    @Override
    protected HierarchicItemRepository<Territory> getRepository() {
        return repository;
    }
}
