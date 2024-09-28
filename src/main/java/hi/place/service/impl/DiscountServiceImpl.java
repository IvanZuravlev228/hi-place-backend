package hi.place.service.impl;

import hi.place.model.Discount;
import hi.place.repository.DiscountRepository;
import hi.place.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;
    private final DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration;

    @Override
    public Page<Discount> findByCurrencyDate(Long today, Pageable pageable) {
        return discountRepository.findDiscountsByCurrentDate(today, pageable);
    }

    @Override
    public Page<Discount> findByCurrentDateAndTypeOfServiceId(Long today, Long typeOfServiceId, Pageable pageable) {
        return discountRepository.findDiscountsByCurrentDateAndTypeOfService(today, typeOfServiceId, pageable);
    }

    @Override
    public Page<Discount> findByCurrentDateAndUserId(Long today, Long userId, Pageable pageable) {
        return discountRepository.findDiscountsByCurrentDateAndUserId(userId, today, pageable);
    }

    @Override
    public Discount save(Discount discount) {
        return discountRepository.save(discount);
    }

    @Override
    public void deleteById(Long id) {
        discountRepository.deleteById(id);
    }

    @Override
    public void updatePhotoUrl(String photoUrl, Long discountId) {
        discountRepository.updatePhotoUrl(photoUrl, discountId);
    }

    @Override
    public Discount findById(Long id) {
        return discountRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't find discount with id: " + id));
    }
}
