package ru.spb.iac.storager.server.domain.periods;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PeriodMapper {

    @Transactional(readOnly = true)
    public Period intoEntity(final PeriodProperties properties) {
        return new Period(properties.getCode(), properties.getTitle());
    }

    @Transactional(readOnly = true)
    public PeriodInfo intoInfo(final Period entity) {
        return new PeriodInfo(entity.getCode(), entity.getTitle());
    }
}
