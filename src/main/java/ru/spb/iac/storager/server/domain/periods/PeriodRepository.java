package ru.spb.iac.storager.server.domain.periods;

import java.util.List;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PeriodRepository extends Repository<Period, Integer> {

    List<Period> findAll();

    Period findByCode(String code);
}
