package hi.place.service.impl.user;

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
    private final PriceRepository priceRepository;
    private final UserService userService;
    private final ServiceItemService serviceItemService;

    @Override
    public List<Price> getAllByUserId(Long id) {
        return priceRepository.getAllByUser_Id(id);
    }

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
}
