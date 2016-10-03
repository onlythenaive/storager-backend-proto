package ru.spb.iac.storager.server.domain.periods;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PeriodMapper {

    public PeriodInfo intoInfo(final Period entity) {
        return new PeriodInfo(entity.getCode(), entity.getTitle());
    }
}
