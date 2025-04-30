package hi.place.service;

import hi.place.dto.price.AvgPriceByServiceItemDTO;
import hi.place.dto.price.PriceProfileResponseDto;
import hi.place.model.user.Price;

import java.util.List;

public interface PriceService {
    List<PriceProfileResponseDto> getAllByTypeOfServiceIdAndUserId(Long typeOfServiceId, Long userId);

    List<Price> addToUser(List<Price> price);

    Price getById(Long id);

    Price update(Price price, Long previousPriceId);

    List<PriceProfileResponseDto> getAllServiceItemsWithoutPrice(Long typeOfServiceId, Long userId);

    void deleteById(Long id);

    List<AvgPriceByServiceItemDTO> getAveragePriceByServiceItemForUser(Long userId);
}
