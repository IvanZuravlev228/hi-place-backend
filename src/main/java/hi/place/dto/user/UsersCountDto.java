package hi.place.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersCountDto {
    private Long salonUsers;
    private Long masterUsers;

    public UsersCountDto(Long salonUsers, Long masterUsers) {
        this.salonUsers = salonUsers;
        this.masterUsers = masterUsers;
    }
}
