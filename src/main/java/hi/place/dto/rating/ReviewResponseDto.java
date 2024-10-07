package hi.place.dto.rating;

import lombok.Data;

@Data
public class ReviewResponseDto {
    private Long id;
    private String feedback;
    private Long addedDate;
    private Double point;
    private Long userId;
    private Long clientId;
}
