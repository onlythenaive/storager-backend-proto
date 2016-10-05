package ru.spb.iac.storager.server.domain.patches;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PatchRepository extends PagingAndSortingRepository<Patch, Integer> {

    @Transactional(readOnly = true)
    @Query("SELECT p FROM Patch AS p " +
            "WHERE p.provider.title LIKE :providerTitle AND p.status LIKE :status " +
            "AND p.createdAt >= :createdSince AND p.createdAt <= :createdUntil")
    Page<Patch> findPageWithFilter(@Param("providerTitle") String providerTitle,
                                   @Param("status") String status,
                                   @Param("createdSince") Instant createdSince,
                                   @Param("createdUntil") Instant createdUntil,
                                   Pageable pageable);
}
