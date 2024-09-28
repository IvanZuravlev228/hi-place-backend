package hi.place.dto.discount;

import lombok.Data;

@Data
public class DiscountRequestDto {
    private String title;
    private String description;
    private Long startDate;
    private Long endDate;
    private Integer discount;
    private Long userId;
    private Long typeOfServiceId;
}
