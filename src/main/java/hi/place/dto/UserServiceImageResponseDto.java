package hi.place.dto;

import lombok.Data;

@Data
public class UserServiceImageResponseDto {
    private Long id;
    private Long typeOfServiceId;
    private Long userId;
    private String path;

    public UserServiceImageResponseDto(Long id, Long typeOfServiceId, Long userId, String path) {
        this.id = id;
        this.typeOfServiceId = typeOfServiceId;
        this.userId = userId;
        this.path = path;
    }
}
