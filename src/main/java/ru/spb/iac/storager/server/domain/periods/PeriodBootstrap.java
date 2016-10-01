package ru.spb.iac.storager.server.domain.periods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableList;

@Component
@Transactional
@Profile("dev.local")
public class PeriodBootstrap {

    @Autowired
    private PeriodBootstrapRepository periodRepository;

    public void run() {
        periodRepository.save(ImmutableList.of(
                new Period("DAY", "День"),
                new Period("WEEK", "Неделя"),
                new Period("MONTH", "Месяц"),
                new Period("QUARTER", "Квартал"),
                new Period("HALF_YEAR", "Полугодие"),
                new Period("YEAR", "Год")
        ));
    }
}
