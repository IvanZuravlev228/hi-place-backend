package hi.place.dto.discount;

import lombok.Data;

@Data
public class DiscountResponseDto {
    private Long id;
    private String title;
    private String description;
    private String photoUrl;
    private Long endDate;
    private Integer discount;
    private Long userId;
    private Long typeOfServiceId;
}
