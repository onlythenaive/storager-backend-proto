package ru.spb.iac.storager.server.domain.indicators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemProperties;

@Component
@Transactional
@Profile("dev.local")
public class IndicatorBootstrap {

    @Autowired
    private IndicatorService service;

    public void run() {
        service.create(properties("1", null, "Показатель 1", "Верхний уровень"));
        service.create(properties("1.1", "1", "Показатель 1.1", "Первый уровень вложенности, дочерний для 1"));
        service.create(properties("1.2", "1", "Показатель 1.2", "Первый уровень вложенности, дочерний для 1"));
        service.create(properties("1.3", "1", "Показатель 1.3", "Первый уровень вложенности, дочерний для 1"));
        service.create(properties("1.3.1", "1.3", "Показатель 1.3.1", "Второй уровень вложенности, дочерний для 1.3"));
        service.create(properties("1.3.2", "1.3", "Показатель 1.3.2", "Второй уровень вложенности, дочерний для 1.3"));
        service.create(properties("1.3.3", "1.3", "Показатель 1.3.3", "Второй уровень вложенности, дочерний для 1.3"));
        service.create(properties("1.4", "1", "Показатель 1.4", "Первый уровень вложенности, дочерний для 1"));
        service.create(properties("2", null, "Показатель 2", "Верхний уровень"));
        service.create(properties("2.1", "2", "Показатель 2.1", "Первый уровень вложенности, дочерний для 2"));
        service.create(properties("2.2", "2", "Показатель 2.2", "Первый уровень вложенности, дочерний для 2"));
        service.create(properties("2.2.1", "2.2", "Показатель 2.2.1", "Второй уровень вложенности, дочерний для 2.2"));
        service.create(properties("2.2.2", "2.2", "Показатель 2.2.2", "Второй уровень вложенности, дочерний для 2.2"));
        service.create(properties("2.3", "2", "Показатель 2.3", "Первый уровень вложенности, дочерний для 2"));
        service.create(properties("3", null, "Показатель 3", "Верхний уровень"));
        service.create(properties("3.1", "3", "Показатель 3.1", "Первый уровень вложенности, дочерний для 3"));
        service.create(properties("3.2", "3", "Показатель 3.2", "Первый уровень вложенности, дочерний для 3"));
        service.create(properties("4", null, "Показатель 4", "Верхний уровень"));
    }

    private HierarchicItemProperties properties(final String code,
                                                final String ascendantCode,
                                                final String title,
                                                final String description) {
        final HierarchicItemProperties properties = new HierarchicItemProperties();
        properties.setCode(code);
        properties.setAscendantCode(ascendantCode);
        properties.setTitle(title);
        properties.setDescription(description);
        return properties;
    }
}
