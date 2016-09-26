package ru.spb.iac.storager.server.data.points;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PointRepository extends JpaRepository<Point, Integer> {
}
