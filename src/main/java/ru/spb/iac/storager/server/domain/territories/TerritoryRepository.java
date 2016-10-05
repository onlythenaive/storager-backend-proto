package ru.spb.iac.storager.server.domain.territories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TerritoryRepository extends JpaRepository<Territory, Integer> {

    @Transactional(readOnly = true)
    Territory findByCode(String code);

    @Transactional(readOnly = true)
    @Query("SELECT t FROM Territory AS t WHERE t.ascendant IS NULL")
    List<Territory> findRoots();
}
