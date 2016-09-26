package ru.spb.iac.storager.server.data.territories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TerritoryBootstrap {

    @Autowired
    private TerritoryService territoryService;

    public void run() {
        territoryService.create(new TerritoryInfo("1", null, "Территория 1"));
        territoryService.create(new TerritoryInfo("1.1", "1", "Территория 1.1"));
    }
}
