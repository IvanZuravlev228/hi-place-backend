package hi.place.service;

import hi.place.dto.UserServiceImageResponseDto;
import hi.place.model.UserServiceImages;

import java.util.List;

public interface UserServiceImagesService {
    List<UserServiceImageResponseDto> getByTypeOfServiceAndUserId(Long typeOfServiceId, Long userId);

    List<UserServiceImageResponseDto> getByMainTypeOfServiceAndUserId(Long mainTypeOfService, Long userId);

    List<UserServiceImageResponseDto> getByUserId(Long userId);

    UserServiceImages save(UserServiceImages userServiceImages);
}
