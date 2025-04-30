package hi.place.repository;

import hi.place.model.Click;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClickRepository extends JpaRepository<Click, Long> {
    @Query("SELECT COUNT(c.id) FROM Click c " +
            "WHERE c.clickedToPhone IS NOT NULL " +
            "AND c.userId = :userId " +
            "AND c.clickedToPhone BETWEEN :start AND :end")
    Integer countClicksByPhoneAndPeriod(
            @Param("userId") Long userId,
            @Param("start") Long start,
            @Param("end") Long end
    );

    @Query("SELECT COUNT(c.id) FROM Click c " +
            "WHERE c.clickedToInstagram IS NOT NULL " +
            "AND c.userId = :userId " +
            "AND c.clickedToInstagram BETWEEN :start AND :end")
    Integer countClicksByInstagramAndPeriod(
            @Param("userId") Long userId,
            @Param("start") Long start,
            @Param("end") Long end
    );

    @Query("SELECT COUNT(c.id) FROM Click c " +
            "WHERE c.clickedToTiktok IS NOT NULL " +
            "AND c.userId = :userId " +
            "AND c.clickedToTiktok BETWEEN :start AND :end")
    Integer countClicksByTiktokAndPeriod(
            @Param("userId") Long userId,
            @Param("start") Long start,
            @Param("end") Long end
    );

    @Query("SELECT COUNT(c.id) FROM Click c " +
            "WHERE c.clickedToTelegram IS NOT NULL " +
            "AND c.userId = :userId " +
            "AND c.clickedToTelegram BETWEEN :start AND :end")
    Integer countClicksByTelegramAndPeriod(
            @Param("userId") Long userId,
            @Param("start") Long start,
            @Param("end") Long end
    );
}
