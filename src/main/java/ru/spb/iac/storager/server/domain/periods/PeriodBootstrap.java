package ru.spb.iac.storager.server.domain.periods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableList;

// TODO: add development-only restriction
@Component
@Transactional
public class PeriodBootstrap {

    @Autowired
    private PeriodRepository periodRepository;

    public void run() {
        periodRepository.save(ImmutableList.of(
                Period.of("DAY", "День"),
                Period.of("WEEK", "Неделя"),
                Period.of("MONTH", "Месяц"),
                Period.of("QUARTER", "Квартал"),
                Period.of("HALF_YEAR", "Полугодие"),
                Period.of("YEAR", "Год")
        ));
    }
}
