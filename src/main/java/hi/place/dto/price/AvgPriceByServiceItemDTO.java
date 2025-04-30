package hi.place.dto.price;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvgPriceByServiceItemDTO {
    private Double avgPrice;
    private Long serviceItemId;
    private String serviceItemName;
    private Double price;

    public AvgPriceByServiceItemDTO(Double avgPrice, Long serviceItemId, String serviceItemName, Double price) {
        this.avgPrice = avgPrice;
        this.serviceItemId = serviceItemId;
        this.serviceItemName = serviceItemName;
        this.price = price;
    }
}
