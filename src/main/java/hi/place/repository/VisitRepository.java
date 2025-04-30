package hi.place.repository;

import hi.place.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    Optional<Visit> findTopByIpAddressAndUserIdOrderByVisitTimeDesc(String ipAddress, Long userId);

    Integer countByUserIdAndVisitTimeBetween(Long userId, Long startTime, Long endTime);
}
