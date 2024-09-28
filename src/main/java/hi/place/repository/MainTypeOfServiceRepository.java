package hi.place.repository;

import hi.place.model.MainTypeOfService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MainTypeOfServiceRepository extends JpaRepository<MainTypeOfService, Long> {
    @Query("SELECT DISTINCT p.mainTypeOfService FROM Price p WHERE p.user.id = :userId")
    List<MainTypeOfService> getAllByUserId(@Param("userId") Long userId);
}
