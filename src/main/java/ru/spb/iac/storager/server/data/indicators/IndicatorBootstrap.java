package ru.spb.iac.storager.server.data.indicators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

// TODO: add development-only restriction
@Component
@Transactional
public class IndicatorBootstrap {

    @Autowired
    private IndicatorService indicatorService;

    public void run() {
        indicatorService.create(IndicatorInfo.of("1", null, "Показатель 1"));
        indicatorService.create(IndicatorInfo.of("1.1", "1", "Показатель 1.1"));
    }
}
