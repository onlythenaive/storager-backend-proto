package ru.spb.iac.storager.server.domain.territories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemRepository;

public interface TerritoryRepository extends JpaRepository<Territory, Integer>, HierarchicItemRepository<Territory> {

    @Override
    @Transactional(readOnly = true)
    Territory findByCode(String code);

    @Override
    @Transactional(readOnly = true)
    @Query("SELECT t FROM Territory AS t WHERE t.ascendant IS NULL")
    List<Territory> findRoots();

    @Transactional(readOnly = true)
    @Query("SELECT t FROM Territory AS t " +
            "LEFT OUTER JOIN t.ascendant AS a " +
            "WHERE t.code LIKE :codePattern " +
            "AND t.title LIKE :titlePattern " +
            "AND (:type = 'ANY' AND (a.code LIKE :ascendantCode OR a.code IS NULL) " +
            "  OR :type = 'ROOT' AND a.code IS NULL " +
            "  OR :type = 'DESCENDANT' AND a.code LIKE :ascendantCode)")
    Page<Territory> findPageWithFilter(@Param("codePattern") String codePattern,
                                       @Param("ascendantCode") String ascendantCode,
                                       @Param("type") String type,
                                       @Param("titlePattern") String status,
                                       Pageable pageable);
}
