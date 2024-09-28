package hi.place.service.mapper;

import hi.place.dto.user.auth.UserFirstRegisterRequestDto;
import hi.place.dto.user.auth.UserSecondRegisterRequestDto;
import hi.place.dto.user.auth.UserThirdRegisterRequestDto;
import hi.place.model.user.User;
import hi.place.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationUserMapper {
    private final UserService userService;

    public User toModalFromFirstRegisterDto(UserFirstRegisterRequestDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhone(dto.getPhone());
        return user;
    }

    public User toModalFromSecondRegisterDto(UserSecondRegisterRequestDto dto) {
        User user = userService.getById(dto.getId());
        user.setExperience(dto.getExperience());
        user.setHomeVisit(dto.getHomeVisit());
        user.setOnlineCounseling(dto.getOnlineCounseling());
        user.setAtSalon(dto.getAtSalon());
        user.setDiscountWithPromo(dto.getDiscountWithPromo());
        user.setType(User.UserType.valueOf(dto.getType()));
        return user;
    }

    public User toModalFromThirdRegisterDto(UserThirdRegisterRequestDto dto) {
        User user = userService.getById(dto.getId());
        user.setViberLink(dto.getViberLink());
        user.setTelegramLink(dto.getTelegramLink());
        user.setTiktokLink(dto.getTiktokLink());
        user.setInstagramLink(dto.getInstagramLink());
        return user;
    }
}
