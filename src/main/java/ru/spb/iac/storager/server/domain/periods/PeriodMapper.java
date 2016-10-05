package ru.spb.iac.storager.server.domain.periods;

import org.springframework.stereotype.Service;

@Service
public class PeriodMapper {

    public PeriodInfo intoInfo(final Period entity) {
        return new PeriodInfo(entity.getCode(), entity.getTitle());
    }
}
