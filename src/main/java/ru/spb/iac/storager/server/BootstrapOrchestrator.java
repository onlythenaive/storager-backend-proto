package ru.spb.iac.storager.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.spb.iac.storager.server.data.periods.PeriodBootstrap;

import javax.annotation.PostConstruct;

@Component
@Lazy(false)
public class BootstrapOrchestrator {

    @Autowired
    private PeriodBootstrap periodBootstrap;

    @PostConstruct
    public void run() {
        this.periodBootstrap.run();
    }
}
