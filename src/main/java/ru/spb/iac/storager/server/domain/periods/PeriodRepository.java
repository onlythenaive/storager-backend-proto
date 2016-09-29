package ru.spb.iac.storager.server.domain.periods;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PeriodRepository extends JpaRepository<Period, Integer> {

    Period findByCode(String code);
}
