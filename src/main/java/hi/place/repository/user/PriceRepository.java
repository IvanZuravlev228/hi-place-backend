package hi.place.repository.user;

import hi.place.dto.price.PriceProfileResponseDto;
import hi.place.model.user.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {
    List<Price> getAllByUser_Id(Long id);

        @Query("select p from Price as p " +
                "join fetch p.serviceItem as si " +
                "join fetch p.mainTypeOfService as mtos " +
                "join fetch p.typeOfService as tos " +
                "join fetch p.user u " +
                "where tos.id = :typeOfServiceId and u.id = :userId")
        List<Price> getAllByTypeOfService_IdAndUser_Id(@Param("typeOfServiceId") Long typeOfServiceId,
                                                       @Param("userId") Long userId);


        @Query("select new hi.place.dto.price.PriceProfileResponseDto(p.id, s.id, s.name, p.price, p.timeUnit) from Price p " +
                "join p.serviceItem s " +
                "join p.typeOfService tos " +
                "join p.user u " +
                "where tos.id = :typeOfServiceId and u.id = :userId")
        List<PriceProfileResponseDto> getAllByTypeOfServiceIdAndUserId(@Param("typeOfServiceId") Long typeOfServiceId,
                                                                       @Param("userId") Long userId);

    @Query("select new hi.place.dto.price.PriceProfileResponseDto(p.id, si.id, si.name, p.price, p.timeUnit) " +
            "from ServiceItem si " +
            "left join Price p on si.id = p.serviceItem.id and p.typeOfService.id = :typeOfServiceId and p.user.id = :userId " +
            "left join si.typeOfService ts " +
            "where ts.id = :typeOfServiceId ")
    List<PriceProfileResponseDto> getAllServiceItemsWithoutPrice(@Param("typeOfServiceId") Long typeOfServiceId,
                                                                 @Param("userId") Long userId);
}
