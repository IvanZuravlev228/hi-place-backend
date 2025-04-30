package hi.place.service.impl.user;

import hi.place.dto.price.AvgPriceByServiceItemDTO;
import hi.place.dto.price.PriceProfileResponseDto;
import hi.place.model.user.Price;
import hi.place.model.user.User;
import hi.place.repository.user.PriceRepository;
import hi.place.service.PriceService;
import hi.place.service.ServiceItemService;
import hi.place.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {
    private static final int AVG_PRICE_INDEX = 0;
    private static final int SERVICE_ITEM_ID_INDEX = 1;
    private static final int SERVICE_NAME_INDEX = 2;
    private static final int USER_PRICE_INDEX = 3;

    private final PriceRepository priceRepository;

    @Override
    public List<PriceProfileResponseDto> getAllByTypeOfServiceIdAndUserId(Long typeOfServiceId, Long userId) {
        return priceRepository.getAllByTypeOfServiceIdAndUserId(typeOfServiceId, userId);
    }

    @Override
    public List<Price> addToUser(List<Price> prices) {
        return priceRepository.saveAll(prices);
    }

    @Override
    public Price getById(Long id) {
        return priceRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't find price by id: " + id));
    }

    @Override
    public Price update(Price price, Long previousPriceId) {
        price.setId(previousPriceId);
        return  priceRepository.save(price);
    }

    @Override
    public List<PriceProfileResponseDto> getAllServiceItemsWithoutPrice(Long typeOfServiceId, Long userId) {
        return priceRepository.getAllServiceItemsWithoutPrice(typeOfServiceId, userId);
    }

    @Override
    public void deleteById(Long id) {
        priceRepository.deleteById(id);
    }

    @Override
    public List<AvgPriceByServiceItemDTO> getAveragePriceByServiceItemForUser(Long userId) {
        List<Object[]> priceObjs = priceRepository.findAveragePriceByServiceItemForUser(userId);
        return priceObjs.stream().map(obj -> new AvgPriceByServiceItemDTO(
                (Double) obj[AVG_PRICE_INDEX],
                (Long) obj[SERVICE_ITEM_ID_INDEX],
                (String) obj[SERVICE_NAME_INDEX],
                (Double) obj[USER_PRICE_INDEX])).toList();
    }
}
