package ru.spb.iac.storager.server.domain.territories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@Profile("dev.local")
public class TerritoryBootstrap {

    @Autowired
    private TerritoryService service;

    public void run() {
        service.create(properties("1", null, "Территория 1", "Верхний уровень"));
        service.create(properties("1.1", "1", "Территория 1.1", "Первый уровень вложенности, дочерняя для 1"));
        service.create(properties("1.2", "1", "Территория 1.2", "Первый уровень вложенности, дочерняя для 1"));
        service.create(properties("1.3", "1", "Территория 1.3", "Первый уровень вложенности, дочерняя для 1"));
        service.create(properties("1.3.1", "1.3", "Территория 1.3.1", "Второй уровень вложенности, дочерняя для 1.3"));
        service.create(properties("1.3.2", "1.3", "Территория 1.3.2", "Второй уровень вложенности, дочерняя для 1.3"));
        service.create(properties("1.3.3", "1.3", "Территория 1.3.3", "Второй уровень вложенности, дочерняя для 1.3"));
        service.create(properties("1.4", "1", "Территория 1.4", "Первый уровень вложенности, дочерняя для 1"));
        service.create(properties("2", null, "Территория 2", "Верхний уровень"));
        service.create(properties("2.1", "2", "Территория 2.1", "Первый уровень вложенности, дочерняя для 2"));
        service.create(properties("2.2", "2", "Территория 2.2", "Первый уровень вложенности, дочерняя для 2"));
        service.create(properties("2.2.1", "2.2", "Территория 2.2.1", "Второй уровень вложенности, дочерняя для 2.2"));
        service.create(properties("2.2.2", "2.2", "Территория 2.2.2", "Второй уровень вложенности, дочерняя для 2.2"));
        service.create(properties("2.3", "2", "Территория 2.3", "Первый уровень вложенности, дочерняя для 2"));
        service.create(properties("3", null, "Территория 3", "Верхний уровень"));
        service.create(properties("3.1", "3", "Территория 3.1", "Первый уровень вложенности, дочерняя для 3"));
        service.create(properties("3.2", "3", "Территория 3.2", "Первый уровень вложенности, дочерняя для 3"));
        service.create(properties("4", null, "Территория 4", "Верхний уровень"));
    }

    private TerritoryProperties properties(final String code,
                                           final String ascendantCode,
                                           final String title,
                                           final String description) {
        final TerritoryProperties properties = new TerritoryProperties();
        properties.setCode(code);
        properties.setAscendantCode(ascendantCode);
        properties.setTitle(title);
        properties.setDescription(description);
        return properties;
    }
}
