package ru.spb.iac.storager.server.data.grants;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface GrantRepository extends JpaRepository<Grant, Integer> {

    @Query("SELECT g FROM Grant AS g WHERE g.indicator.code = :code")
    List<Grant> findByIndicator(@Param("code") String code);

    @Query("SELECT g FROM Grant AS g WHERE g.provider.id = :id")
    List<Grant> findByProvider(@Param("id") Integer id);
}
