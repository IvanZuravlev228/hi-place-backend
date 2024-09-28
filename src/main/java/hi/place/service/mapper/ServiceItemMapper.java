package hi.place.service.mapper;

import hi.place.dto.ServiceItemResponseDto;
import hi.place.model.ServiceItem;
import org.springframework.stereotype.Component;

@Component
public class ServiceItemMapper {
    public ServiceItemResponseDto toDto(ServiceItem model) {
        ServiceItemResponseDto dto = new ServiceItemResponseDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        return dto;
    }
}
