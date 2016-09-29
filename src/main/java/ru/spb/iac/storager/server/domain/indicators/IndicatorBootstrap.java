package ru.spb.iac.storager.server.domain.indicators;

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
        indicatorService.create(createInfo("1", null, "Показатель 1"));
        indicatorService.create(createInfo("1.1", "1", "Показатель 1.1"));
    }

    private IndicatorInfo createInfo(String code, String ascendantCode, String title) {
        IndicatorInfo info = new IndicatorInfo();
        info.setCode(code);
        info.setAscendantCode(ascendantCode);
        info.setTitle(title);
        return info;
    }
}
