package hi.place.dto;

import lombok.Data;

@Data
public class TypeOfServiceCountDto {
    private Long typeOfServiceId;
    private String typeOfServiceName;
    private Long count;

    public TypeOfServiceCountDto(Long typeOfServiceId, String typeOfServiceName, Long count) {
        this.typeOfServiceId = typeOfServiceId;
        this.typeOfServiceName = typeOfServiceName;
        this.count = count;
    }
}
