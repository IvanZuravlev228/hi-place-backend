package hi.place.dto.rating;

import lombok.Data;

@Data
public class ReviewRequestDto {
    private String clientName;
    private String clientEmail;
    private String feedback;
    private Double point;
    private Long userId;
    private Long clientId;
}
