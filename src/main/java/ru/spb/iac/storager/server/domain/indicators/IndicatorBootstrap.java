package ru.spb.iac.storager.server.domain.indicators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemBootstrap;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemProperties;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemService;

@Component
@Profile("dev.local")
public class IndicatorBootstrap extends HierarchicItemBootstrap<Indicator> {

    @Autowired
    private IndicatorService service;

    @Override
    protected List<HierarchicItemProperties> getPropertiesList() {
        final List<HierarchicItemProperties> list = new ArrayList<>();
        list.add(properties("1", null, "Показатель 1", "Верхний уровень"));
        list.add(properties("1.1", "1", "Показатель 1.1", "Первый уровень вложенности, дочерний для 1"));
        list.add(properties("1.2", "1", "Показатель 1.2", "Первый уровень вложенности, дочерний для 1"));
        list.add(properties("1.3", "1", "Показатель 1.3", "Первый уровень вложенности, дочерний для 1"));
        list.add(properties("1.3.1", "1.3", "Показатель 1.3.1", "Второй уровень вложенности, дочерний для 1.3"));
        list.add(properties("1.3.2", "1.3", "Показатель 1.3.2", "Второй уровень вложенности, дочерний для 1.3"));
        list.add(properties("1.3.3", "1.3", "Показатель 1.3.3", "Второй уровень вложенности, дочерний для 1.3"));
        list.add(properties("1.4", "1", "Показатель 1.4", "Первый уровень вложенности, дочерний для 1"));
        list.add(properties("2", null, "Показатель 2", "Верхний уровень"));
        list.add(properties("2.1", "2", "Показатель 2.1", "Первый уровень вложенности, дочерний для 2"));
        list.add(properties("2.2", "2", "Показатель 2.2", "Первый уровень вложенности, дочерний для 2"));
        list.add(properties("2.2.1", "2.2", "Показатель 2.2.1", "Второй уровень вложенности, дочерний для 2.2"));
        list.add(properties("2.2.2", "2.2", "Показатель 2.2.2", "Второй уровень вложенности, дочерний для 2.2"));
        list.add(properties("2.3", "2", "Показатель 2.3", "Первый уровень вложенности, дочерний для 2"));
        list.add(properties("3", null, "Показатель 3", "Верхний уровень"));
        list.add(properties("3.1", "3", "Показатель 3.1", "Первый уровень вложенности, дочерний для 3"));
        list.add(properties("3.2", "3", "Показатель 3.2", "Первый уровень вложенности, дочерний для 3"));
        list.add(properties("4", null, "Показатель 4", "Верхний уровень"));
        return list;
    }

    @Override
    protected HierarchicItemService<Indicator> getService() {
        return service;
    }
}
