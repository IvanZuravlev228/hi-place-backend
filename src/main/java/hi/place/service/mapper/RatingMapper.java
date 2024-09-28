package hi.place.service.mapper;

import hi.place.dto.rating.RatingRequestDto;
import hi.place.dto.rating.RatingResponseDto;
import hi.place.model.user.Rating;
import hi.place.service.ClientService;
import hi.place.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RatingMapper implements RequestResponseMapper<RatingRequestDto, RatingResponseDto, Rating> {
    private final UserService userService;
    private final ClientService clientService;

    @Override
    public Rating toModel(RatingRequestDto dto) {
        Rating model = new Rating();
        model.setFeedback(dto.getFeedback());
        model.setPoint(dto.getPoint());
        model.setUser(userService.getById(dto.getUserId()));
        model.setClient(clientService.getById(dto.getClientId()));
        return model;
    }

    @Override
    public RatingResponseDto toDto(Rating model) {
        RatingResponseDto dto = new RatingResponseDto();
        dto.setId(model.getId());
        dto.setFeedback(model.getFeedback());
        dto.setPoint(model.getPoint());
        dto.setUserId(model.getUser().getId());
        dto.setClientId(model.getClient().getId());
        return dto;
    }
}
