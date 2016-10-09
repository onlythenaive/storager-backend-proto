package ru.spb.iac.storager.server.domain.periods;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PeriodRepository extends JpaRepository<Period, Integer> {

    @Transactional(readOnly = true)
    List<Period> findAll();

    @Transactional(readOnly = true)
    Period findByCode(String code);
}
