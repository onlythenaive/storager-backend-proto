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
            "WHERE t.code LIKE :codePattern " +
            "AND t.title LIKE :titlePattern " +
            "AND ((:root = 0 AND (t.ascendant.code LIKE :ascendantCode OR t.ascendant IS NULL)) " +
            "   OR ( :root = 1 AND t.ascendant IS NULL))")

    Page<Territory> findPageWithFilter(@Param("codePattern") String codePattern,
                                       @Param("ascendantCode") String ascendantCode,
                                       @Param("root") int root,
                                       @Param("titlePattern") String status,
                                       Pageable pageable);
}
