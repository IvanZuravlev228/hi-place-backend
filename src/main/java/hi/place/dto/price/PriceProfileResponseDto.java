package hi.place.dto.price;

import lombok.Data;

@Data
public class PriceProfileResponseDto {
    private Long id;
    private Long serviceItemId;
    private String serviceItemName;
    private Double price;
    private String timeUnit;

    public PriceProfileResponseDto(Long id, Long serviceItemId, String serviceItemName, Double price, String timeUnit) {
        this.id = id;
        this.serviceItemId = serviceItemId;
        this.serviceItemName = serviceItemName;
        this.price = price;
        this.timeUnit = timeUnit;
    }
}
