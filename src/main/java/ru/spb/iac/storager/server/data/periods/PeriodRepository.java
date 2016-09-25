package ru.spb.iac.storager.server.data.periods;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PeriodRepository extends JpaRepository<Period, Integer> {

}
