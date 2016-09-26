package ru.spb.iac.storager.server;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import ru.spb.iac.storager.server.data.periods.PeriodBootstrap;
import ru.spb.iac.storager.server.data.users.UserBootstrap;

@Component
@Lazy(false)
public class BootstrapOrchestrator {

    @Autowired
    private PeriodBootstrap periodBootstrap;

    @Autowired
    private UserBootstrap userBootstrap;

    @PostConstruct
    public void run() {
        userBootstrap.run();
        periodBootstrap.run();
    }
}
