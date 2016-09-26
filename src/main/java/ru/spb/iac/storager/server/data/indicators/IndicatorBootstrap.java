package ru.spb.iac.storager.server.data.indicators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IndicatorBootstrap {

    @Autowired
    private IndicatorService indicatorService;

    public void run() {
        indicatorService.create(new IndicatorInfo("1", null, "Индикатор 1"));
        indicatorService.create(new IndicatorInfo("1.1", "1", "Индикатор 1.1"));
    }
}
