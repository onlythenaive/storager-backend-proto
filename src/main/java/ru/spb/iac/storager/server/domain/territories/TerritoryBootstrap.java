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
        territoryService.create(createInfo("1", null, "Территория 1"));
        territoryService.create(createInfo("1.1", "1", "Территория 1.1"));
    }

    private TerritoryInfo createInfo(String code, String ascendantCode, String title) {
        TerritoryInfo info = new TerritoryInfo();
        info.setCode(code);
        info.setAscendantCode(ascendantCode);
        info.setTitle(title);
        return info;
    }
}
