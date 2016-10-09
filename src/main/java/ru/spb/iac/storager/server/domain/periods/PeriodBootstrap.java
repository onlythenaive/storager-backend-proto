package ru.spb.iac.storager.server.domain.periods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Profile("dev.local")
public class PeriodBootstrap {

    @Autowired
    private PeriodService service;

    @Transactional
    public void run() {
        service.bootstrap(properties("DAY", "День"));
        service.bootstrap(properties("WEEK", "Неделя"));
        service.bootstrap(properties("MONTH", "Месяц"));
        service.bootstrap(properties("QUARTER", "Квартал"));
        service.bootstrap(properties("HALF_YEAR", "Полугодие"));
        service.bootstrap(properties("YEAR", "Год"));
    }

    private PeriodProperties properties(final String code, final String title) {
        final PeriodProperties properties = new PeriodProperties();
        properties.setCode(code);
        properties.setTitle(title);
        return properties;
    }
}
