package hi.place.service;

import hi.place.model.Discount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiscountService {
    Page<Discount> findByCurrencyDate(Long today, Pageable pageable);

    Page<Discount> findByCurrentDateAndTypeOfServiceId(Long today, Long typeOfServiceId, Pageable pageable);

    Page<Discount> findByCurrentDateAndUserId(Long today, Long userId, Pageable pageable);

    Discount save(Discount discount);

    void deleteById(Long id);

    void updatePhotoUrl(String photoUrl, Long discountId);

    Discount findById(Long id);
}
