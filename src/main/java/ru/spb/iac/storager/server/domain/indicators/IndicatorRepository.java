package ru.spb.iac.storager.server.domain.indicators;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemRepository;

public interface IndicatorRepository extends JpaRepository<Indicator, Integer>, HierarchicItemRepository<Indicator> {

    @Override
    @Transactional(readOnly = true)
    Indicator findByCode(String code);

    @Override
    @Transactional(readOnly = true)
    @Query("SELECT i FROM Indicator AS i WHERE i.ascendant IS NULL")
    List<Indicator> findRoots();

    @Transactional(readOnly = true)
    @Query("SELECT i FROM Indicator AS i " +
            "WHERE i.code LIKE :codePattern " +
            "AND i.code NOT IN :grants " +
            "AND i.title LIKE :titlePattern " +
            "AND (((i.ascendant.code LIKE :ascendantCode AND :root = 0) OR i.ascendant IS NULL) OR (i.ascendant IS NULL AND :root = 1))")
    Page<Indicator> findPageWithFilter(@Param("codePattern") String codePattern,
                                       @Param("ascendantCode") String ascendantCode,
                                       @Param("root") int root,
                                       @Param("titlePattern") String status,
                                       @Param("grants") Set<String> grants,
                                       Pageable pageable);
}
