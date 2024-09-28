package hi.place.repository;

import hi.place.dto.UserServiceImageResponseDto;
import hi.place.model.UserServiceImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserServiceImagesRepository extends JpaRepository<UserServiceImages, Long> {
    @Query("select new hi.place.dto.UserServiceImageResponseDto(usi.id, tos.id, u.id, usi.path) from UserServiceImages usi " +
            "join usi.typeOfService tos " +
            "join usi.user u " +
            "where tos.id = :typeOfServiceId and u.id = :userId order by usi.id asc LIMIT 5")
    List<UserServiceImageResponseDto> findUserServiceImageDtosByTypeOfService_IdAndUser_Id(@Param("typeOfServiceId") Long typeOfServiceId,
                                                                                           @Param("userId") Long userId);

    @Query("select new hi.place.dto.UserServiceImageResponseDto(usi.id, mt.id, u.id, usi.path) from UserServiceImages usi " +
            "join usi.typeOfService.mainType mt " +
            "join usi.user u " +
            "where mt.id = :mainTypeOfServiceId and u.id = :userId order by usi.id asc LIMIT 5")
    List<UserServiceImageResponseDto> findUserServiceImageDtosByMainTypeOfService_IdAndUser_Id(@Param("mainTypeOfServiceId") Long mainTypeOfServiceId,
                                                                                               @Param("userId") Long userId);

    List<UserServiceImageResponseDto> findByUser_Id(Long userId);
}
