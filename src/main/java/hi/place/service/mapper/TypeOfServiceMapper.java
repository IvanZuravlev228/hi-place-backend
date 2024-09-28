package hi.place.service.mapper;

import hi.place.dto.TypeOfServiceResponseDto;
import hi.place.model.TypeOfService;
import org.springframework.stereotype.Component;

@Component
public class TypeOfServiceMapper {
    public TypeOfServiceResponseDto toDto(TypeOfService model) {
        TypeOfServiceResponseDto dto = new TypeOfServiceResponseDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        return dto;
    }
}
