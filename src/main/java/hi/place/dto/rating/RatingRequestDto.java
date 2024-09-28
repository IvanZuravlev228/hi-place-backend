package hi.place.dto.rating;

import lombok.Data;

@Data
public class RatingRequestDto {
    private String feedback;
    private Double point;
    private Long userId;
    private Long clientId;
}
