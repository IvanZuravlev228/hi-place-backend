package hi.place.dto.user;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String logoURL;
    private Double experience;
    private String phone;
    private String tiktokLink;
    private String instagramLink;
    private String telegramLink;
    private String viberLink;
    private Boolean homeVisit;
    private Boolean onlineCounseling;
    private Boolean atSalon;
    private Integer discountWithPromo = 0;
    private String type;
    private Double avg;
}
