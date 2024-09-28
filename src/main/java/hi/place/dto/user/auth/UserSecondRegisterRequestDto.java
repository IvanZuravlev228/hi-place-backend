package hi.place.dto.user.auth;

import lombok.Data;

@Data
public class UserSecondRegisterRequestDto {
    private Long id;
    private Double experience;
    private Boolean homeVisit;
    private Boolean onlineCounseling;
    private Boolean atSalon;
    private Integer discountWithPromo;
    private String type;
}
