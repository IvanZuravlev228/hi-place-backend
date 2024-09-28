package hi.place.dto.user.auth;

import lombok.Data;

@Data
public class UserThirdRegisterRequestDto {
    private Long id;
    private String tiktokLink;
    private String instagramLink;
    private String telegramLink;
    private String viberLink;
}
