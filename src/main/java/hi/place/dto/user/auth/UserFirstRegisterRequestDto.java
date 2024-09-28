package hi.place.dto.user.auth;

import lombok.Data;

@Data
public class UserFirstRegisterRequestDto {
    private String name;
    private String email;
    private String phone;
    private String password;
}
