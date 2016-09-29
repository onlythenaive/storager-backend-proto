package ru.spb.iac.storager.server.domain.indicators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@Profile("dev.local")
public class IndicatorBootstrap {

    @Autowired
    private IndicatorService indicatorService;

    public void run() {
        indicatorService.create(createData("1", null, "Показатель 1"));
        indicatorService.create(createData("1.1", "1", "Показатель 1.1"));
    }

    private IndicatorData createData(final String code, final String ascendantCode, final String title) {
        final IndicatorData data = new IndicatorData();
        data.setCode(code);
        data.setAscendantCode(ascendantCode);
        data.setTitle(title);
        return data;
    }
}
