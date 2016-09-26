package ru.spb.iac.storager.server.data.territories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

// TODO: add development-only restriction
@Component
@Transactional
public class TerritoryBootstrap {

    @Autowired
    private TerritoryService territoryService;

    public void run() {
        territoryService.create(TerritoryInfo.of("1", null, "Территория 1"));
        territoryService.create(TerritoryInfo.of("1.1", "1", "Территория 1.1"));
    }
}
