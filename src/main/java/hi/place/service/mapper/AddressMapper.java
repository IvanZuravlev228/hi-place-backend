package hi.place.service.mapper;

import hi.place.dto.address.AddressRequestDto;
import hi.place.dto.address.AddressResponseDto;
import hi.place.model.address.Address;
import hi.place.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressMapper implements RequestResponseMapper<AddressRequestDto, AddressResponseDto, Address> {
    private final UserService userService;

    @Override
    public Address toModel(AddressRequestDto dto) {
        Address model = new Address();
        model.setCountry(dto.getCountry());
        model.setCity(dto.getCity());
        model.setBorough(dto.getBorough());
        model.setRoad(dto.getRoad());
        model.setHouseNumber(dto.getHouseNumber());
        model.setSuburb(dto.getSuburb());
        model.setLat(dto.getLat());
        model.setLon(dto.getLon());
        model.setUser(userService.getById(dto.getUserId()));
        return model;
    }

    @Override
    public AddressResponseDto toDto(Address model) {
        AddressResponseDto dto = new AddressResponseDto();
        dto.setId(model.getId());
        dto.setCountry(model.getCountry());
        dto.setCity(model.getCity());
        dto.setBorough(model.getBorough());
        dto.setRoad(model.getRoad());
        dto.setHouseNumber(model.getHouseNumber());
        dto.setSuburb(model.getSuburb());
        dto.setLat(model.getLat());
        dto.setLon(model.getLon());
        return dto;
    }
}
