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
    private TerritoryService territoryService;

    public void run() {
        territoryService.create(generate("1", null, "Территория 1", null));
        territoryService.create(generate("1.1", "1", "Территория 1.1", null));
    }

    private TerritoryProperties generate(final String code,
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
