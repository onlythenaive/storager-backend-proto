package ru.spb.iac.storager.server.domain.indicators;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IndicatorRepository extends JpaRepository<Indicator, Integer> {

    Indicator findByCode(String code);

    @Query("SELECT i FROM Indicator AS i WHERE i.ascendant.code = :code")
    List<Indicator> findDescendants(@Param("code") String code);

    @Query("SELECT i FROM Indicator AS i WHERE i.ascendant IS NULL")
    List<Indicator> findRoots();
}
