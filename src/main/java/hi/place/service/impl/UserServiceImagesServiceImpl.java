package hi.place.service.impl;

import hi.place.dto.UserServiceImageResponseDto;
import hi.place.model.UserServiceImages;
import hi.place.repository.UserServiceImagesRepository;
import hi.place.service.UserServiceImagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImagesServiceImpl implements UserServiceImagesService {
    private final UserServiceImagesRepository userServiceImagesRepository;

    @Override
    public List<UserServiceImageResponseDto> getByTypeOfServiceAndUserId(Long typeOfServiceId, Long userId) {
        return userServiceImagesRepository.findUserServiceImageDtosByTypeOfService_IdAndUser_Id(typeOfServiceId, userId);
    }

    @Override
    public List<UserServiceImageResponseDto> getByMainTypeOfServiceAndUserId(Long mainTypeOfService, Long userId) {
        return userServiceImagesRepository.findUserServiceImageDtosByMainTypeOfService_IdAndUser_Id(mainTypeOfService, userId);
    }

    @Override
    public List<UserServiceImageResponseDto> getByUserId(Long userId) {
        return userServiceImagesRepository.findByUser_Id(userId);
    }

    @Override
    public UserServiceImages save(UserServiceImages userServiceImages) {
        return userServiceImagesRepository.save(userServiceImages);
    }
}
