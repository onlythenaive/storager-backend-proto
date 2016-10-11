package ru.spb.iac.storager.server.domain;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import ru.spb.iac.storager.server.domain.indicators.IndicatorBootstrap;
import ru.spb.iac.storager.server.domain.patches.PatchBootstrap;
import ru.spb.iac.storager.server.domain.periods.PeriodBootstrap;
import ru.spb.iac.storager.server.domain.providers.ProviderBootstrap;
import ru.spb.iac.storager.server.domain.territories.TerritoryBootstrap;
import ru.spb.iac.storager.server.domain.users.UserBootstrap;

@Component
@Lazy(false)
@Profile("bootstrap")
public class BootstrapOrchestrator {

    @Autowired
    private PeriodBootstrap periodBootstrap;

    @Autowired
    private TerritoryBootstrap territoryBootstrap;

    @Autowired
    private IndicatorBootstrap indicatorBootstrap;

    @Autowired
    private PatchBootstrap patchBootstrap;

    @Autowired
    private ProviderBootstrap providerBootstrap;

    @Autowired
    private UserBootstrap userBootstrap;

    @PostConstruct
    public void run() {
        userBootstrap.run();
        periodBootstrap.run();
        territoryBootstrap.run();
        indicatorBootstrap.run();
        providerBootstrap.run();
        patchBootstrap.run();
    }
}
