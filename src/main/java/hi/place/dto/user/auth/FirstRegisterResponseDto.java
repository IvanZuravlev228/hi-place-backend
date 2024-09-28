package hi.place.dto.user.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FirstRegisterResponseDto {
    private Long id;

    public FirstRegisterResponseDto() {}

    public FirstRegisterResponseDto(Long id) {this.id = id;}
}
