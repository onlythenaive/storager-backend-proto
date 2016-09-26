package ru.spb.iac.storager.server.data.territories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TerritoryRepository extends JpaRepository<Territory, Integer> {

    Territory findByCode(String code);

    @Query("FROM Territory t WHERE t.ascendant.code = :code")
    List<Territory> findDescendantsByCode(@Param("code") String code);

    @Query("FROM Territory t WHERE t.ascendant IS NULL")
    List<Territory> findRoots();
}
