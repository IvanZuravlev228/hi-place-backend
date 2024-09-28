package hi.place.service.mapper;

import hi.place.dto.discount.DiscountRequestDto;
import hi.place.dto.discount.DiscountResponseDto;
import hi.place.model.Discount;
import hi.place.service.TypeOfServiceService;
import hi.place.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DiscountMapper implements RequestResponseMapper<DiscountRequestDto, DiscountResponseDto, Discount> {
    private final UserService userService;
    private final TypeOfServiceService typeOfServiceService;

    @Override
    public Discount toModel(DiscountRequestDto dto) {
        Discount model = new Discount();
        model.setTitle(dto.getTitle());
        model.setDescription(dto.getDescription());
        model.setStartDate(dto.getStartDate());
        model.setEndDate(dto.getEndDate());
        model.setDiscount(dto.getDiscount());
        model.setUser(userService.getById(dto.getUserId()));
        model.setTypeOfService(typeOfServiceService.getById(dto.getTypeOfServiceId()));
        return model;
    }

    @Override
    public DiscountResponseDto toDto(Discount model) {
        DiscountResponseDto dto = new DiscountResponseDto();
        dto.setId(model.getId());
        dto.setTitle(model.getTitle());
        dto.setDescription(model.getDescription());
        dto.setPhotoUrl(model.getPhotoUrl());
        dto.setEndDate(model.getEndDate());
        dto.setDiscount(model.getDiscount());
        dto.setUserId(model.getUser().getId());
        dto.setTypeOfServiceId(model.getTypeOfService().getId());
        return dto;
    }
}
