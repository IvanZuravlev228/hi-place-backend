package hi.place.dto.address;

import lombok.Data;

@Data
public class AddressRequestDto {
    private String country;
    private String city;
    private String borough;
    private String road;
    private String houseNumber;
    private String suburb;
    private Double lat;
    private Double lon;
    private Long userId;
}
