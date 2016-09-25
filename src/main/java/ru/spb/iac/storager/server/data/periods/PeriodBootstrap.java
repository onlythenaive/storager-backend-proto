package ru.spb.iac.storager.server.data.periods;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PeriodBootstrap {

    @Autowired
    private PeriodRepository periodRepository;

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
