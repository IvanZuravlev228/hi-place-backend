package hi.place.service.mapper;

import hi.place.dto.rating.ReviewRequestDto;
import hi.place.dto.rating.ReviewResponseDto;
import hi.place.model.user.Review;
import hi.place.service.ClientService;
import hi.place.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewMapper implements RequestResponseMapper<ReviewRequestDto, ReviewResponseDto, Review> {
    private final UserService userService;
    private final ClientService clientService;

    @Override
    public Review toModel(ReviewRequestDto dto) {
        Review model = new Review();
        model.setFeedback(dto.getFeedback());
        model.setPoint(dto.getPoint());
        model.setUser(userService.getById(dto.getUserId()));
        model.setClient(clientService.getById(dto.getClientId()));
        return model;
    }

    @Override
    public ReviewResponseDto toDto(Review model) {
        ReviewResponseDto dto = new ReviewResponseDto();
        dto.setId(model.getId());
        dto.setFeedback(model.getFeedback());
        dto.setAddedDate(model.getAddedDate());
        dto.setPoint(model.getPoint());
        dto.setUserId(model.getUser().getId());
        dto.setClientId(model.getClient().getId());
        return dto;
    }
}
