package hi.place.dto.price;

import lombok.Data;

@Data
public class PriceResponseDto {
    private Long id;
    private Long serviceItemId;
    private Long typeOfServiceId;
    private Long mainTypeOfServiceId;
    private Long userId;
    private Double price;
    private String timeUnit;
}
