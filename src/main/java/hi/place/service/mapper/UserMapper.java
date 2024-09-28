package hi.place.service.mapper;

import hi.place.dto.user.UserRequestDto;
import hi.place.dto.user.UserResponseDto;
import hi.place.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements RequestResponseMapper<UserRequestDto, UserResponseDto, User> {
    @Override
    public User toModel(UserRequestDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setLogoURL(dto.getLogoURL());
        user.setExperience(dto.getExperience());
        user.setPhone(dto.getPhone());
        user.setTiktokLink(dto.getTiktokLink());
        user.setInstagramLink(dto.getInstagramLink());
        user.setTelegramLink(dto.getTelegramLink());
        user.setViberLink(dto.getViberLink());
        user.setHomeVisit(dto.getHomeVisit());
        user.setOnlineCounseling(dto.getOnlineCounseling());
        user.setAtSalon(dto.getAtSalon());
        user.setDiscountWithPromo(dto.getDiscountWithPromo());
        user.setType(User.UserType.valueOf(dto.getType()));
        return user;
    }

    @Override
    public UserResponseDto toDto(User model) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setEmail(model.getEmail());
        dto.setLogoURL(model.getLogoURL());
        dto.setExperience(model.getExperience());
        dto.setPhone(model.getPhone());
        dto.setTiktokLink(model.getTiktokLink());
        dto.setInstagramLink(model.getInstagramLink());
        dto.setTelegramLink(model.getTelegramLink());
        dto.setViberLink(model.getViberLink());
        dto.setHomeVisit(model.getHomeVisit());
        dto.setOnlineCounseling(model.getOnlineCounseling());
        dto.setAtSalon(model.getAtSalon());
        dto.setDiscountWithPromo(model.getDiscountWithPromo());
        dto.setType(model.getType().toString());
        dto.setAvg(model.getAvg());
        return dto;
    }
}
