package hi.place.service.mapper;

import hi.place.dto.price.PriceRequestDto;
import hi.place.dto.price.PriceResponseDto;
import hi.place.model.ServiceItem;
import hi.place.model.user.Price;
import hi.place.service.ServiceItemService;
import hi.place.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceMapper implements RequestResponseMapper<PriceRequestDto, PriceResponseDto, Price> {
    private final ServiceItemService serviceItemService;
    private final UserService userService;

    @Override
    public Price toModel(PriceRequestDto dto) {
        Price model = new Price();
        ServiceItem serviceItem = serviceItemService.getInitializeById(dto.getServiceItemId());
        model.setServiceItem(serviceItem);
        model.setTypeOfService(serviceItem.getTypeOfService());
        model.setMainTypeOfService(serviceItem.getTypeOfService().getMainType());
        model.setUser(userService.getById(dto.getUserId()));
        model.setPrice(dto.getPrice());
        model.setTimeUnit(dto.getTimeUnit());
        return model;
    }

    @Override
    public PriceResponseDto toDto(Price model) {
        PriceResponseDto dto = new PriceResponseDto();
        dto.setId(model.getId());
        dto.setServiceItemId(model.getServiceItem().getId());
        dto.setTypeOfServiceId(model.getTypeOfService().getId());
        dto.setMainTypeOfServiceId(model.getMainTypeOfService().getId());
        dto.setUserId(model.getUser().getId());
        dto.setPrice(model.getPrice());
        dto.setTimeUnit(model.getTimeUnit());
        return dto;
    }
}
