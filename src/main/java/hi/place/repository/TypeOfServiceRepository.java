package hi.place.repository;

import hi.place.dto.TypeOfServiceCountDto;
import hi.place.model.TypeOfService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TypeOfServiceRepository extends JpaRepository<TypeOfService, Long> {
    List<TypeOfService> findByMainType_Id(Long mainTypeId);

    @Query("SELECT new hi.place.dto.TypeOfServiceCountDto(p.typeOfService.id, p.typeOfService.name, COUNT(p.typeOfService.id)) " +
            "FROM Price p " +
            "where p.user.id = :userId  " +
            "GROUP BY p.typeOfService.id")

    List<TypeOfServiceCountDto> getTypeOfServiceAnfCountByUserId(@Param("userId") Long userId);

    @Query(value = "select distinct ts.* from type_of_service as ts " +
            "join discount as d on d.type_of_service_id = ts.id where d.end_date > :currentDate", nativeQuery = true)
    List<TypeOfService> getAllWithDiscount(@Param("currentDate") Long currentDate);
}
