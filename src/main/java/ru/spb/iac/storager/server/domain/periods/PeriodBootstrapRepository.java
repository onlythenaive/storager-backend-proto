package ru.spb.iac.storager.server.domain.periods;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Profile("dev.local")
public interface PeriodBootstrapRepository extends Repository<Period, Integer> {

    Iterable<Period> save(Iterable<Period> periods);
}
