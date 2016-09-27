package ru.spb.iac.storager.server.data.territories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TerritoryRepository extends JpaRepository<Territory, Integer> {

    Territory findByCode(String code);

    @Query("SELECT t FROM Territory AS t WHERE t.ascendant.code = :code")
    List<Territory> findDescendants(@Param("code") String code);

    @Query("SELECT t FROM Territory AS t WHERE t.ascendant IS NULL")
    List<Territory> findRoots();
}
