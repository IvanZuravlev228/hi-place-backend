package hi.place.repository;

import hi.place.model.Discount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
    @Query("SELECT d FROM Discount d " +
            "WHERE :currentDate BETWEEN d.startDate AND d.endDate")
    Page<Discount> findDiscountsByCurrentDate(@Param("currentDate") Long currentDate, Pageable pageable);

    @Query("SELECT d FROM Discount d " +
            "WHERE :currentDate BETWEEN d.startDate AND d.endDate " +
            "AND d.typeOfService.id = :typeOfServiceId")
    Page<Discount> findDiscountsByCurrentDateAndTypeOfService(
            @Param("currentDate") Long currentDate,
            @Param("typeOfServiceId") Long typeOfServiceId,
            Pageable pageable);

    @Query("select d from Discount d where d.user.id = :userId " +
            "and :currentDate between d.startDate and d.endDate")
    Page<Discount> findDiscountsByCurrentDateAndUserId(@Param("userId") Long userId,
                                                       @Param("currentDate") Long currentDate,
                                                       Pageable pageable);

    @Modifying
    @Transactional
    @Query("update Discount d set d.photoUrl = :photoUrl where d.id = :discountId")
    void updatePhotoUrl(@Param("photoUrl") String photoUrl, @Param("discountId") Long discountId);
}
