package ru.spb.iac.storager.server.domain.territories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemBootstrap;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemProperties;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemService;

@Component
@Profile("bootstrap")
public class TerritoryBootstrap extends HierarchicItemBootstrap<Territory> {

    @Autowired
    private TerritoryService service;

    @Override
    protected List<HierarchicItemProperties> getPropertiesList() {
        final List<HierarchicItemProperties> list = new ArrayList<>();
        list.add(properties("1", null, "Территория 1", "Верхний уровень"));
        list.add(properties("1.1", "1", "Территория 1.1", "Первый уровень вложенности, дочерняя для 1"));
        list.add(properties("1.2", "1", "Территория 1.2", "Первый уровень вложенности, дочерняя для 1"));
        list.add(properties("1.3", "1", "Территория 1.3", "Первый уровень вложенности, дочерняя для 1"));
        list.add(properties("1.3.1", "1.3", "Территория 1.3.1", "Второй уровень вложенности, дочерняя для 1.3"));
        list.add(properties("1.3.2", "1.3", "Территория 1.3.2", "Второй уровень вложенности, дочерняя для 1.3"));
        list.add(properties("1.3.3", "1.3", "Территория 1.3.3", "Второй уровень вложенности, дочерняя для 1.3"));
        list.add(properties("1.4", "1", "Территория 1.4", "Первый уровень вложенности, дочерняя для 1"));
        list.add(properties("2", null, "Территория 2", "Верхний уровень"));
        list.add(properties("2.1", "2", "Территория 2.1", "Первый уровень вложенности, дочерняя для 2"));
        list.add(properties("2.2", "2", "Территория 2.2", "Первый уровень вложенности, дочерняя для 2"));
        list.add(properties("2.2.1", "2.2", "Территория 2.2.1", "Второй уровень вложенности, дочерняя для 2.2"));
        list.add(properties("2.2.2", "2.2", "Территория 2.2.2", "Второй уровень вложенности, дочерняя для 2.2"));
        list.add(properties("2.3", "2", "Территория 2.3", "Первый уровень вложенности, дочерняя для 2"));
        list.add(properties("3", null, "Территория 3", "Верхний уровень"));
        list.add(properties("3.1", "3", "Территория 3.1", "Первый уровень вложенности, дочерняя для 3"));
        list.add(properties("3.2", "3", "Территория 3.2", "Первый уровень вложенности, дочерняя для 3"));
        list.add(properties("4", null, "Территория 4", "Верхний уровень"));
        return list;
    }

    @Override
    protected HierarchicItemService<Territory> getService() {
        return service;
    }
}
