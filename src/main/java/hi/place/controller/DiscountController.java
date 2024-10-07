package hi.place.controller;

import hi.place.dto.discount.DiscountRequestDto;
import hi.place.dto.discount.DiscountResponseDto;
import hi.place.model.Discount;
import hi.place.service.DiscountService;
import hi.place.service.mapper.RequestResponseMapper;
import hi.place.util.DateCreater;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/discounts")
@RequiredArgsConstructor
public class DiscountController {
    private final DiscountService discountService;
    private final RequestResponseMapper<DiscountRequestDto, DiscountResponseDto, Discount> discountMapper;

    @GetMapping
    public ResponseEntity<Page<DiscountResponseDto>> getAll(Pageable pageable) {
        Page<Discount> discounts = discountService.findByCurrencyDate(DateCreater.getCurrentStartOfDayAsSeconds(), pageable);
        return new ResponseEntity<>(discounts.map(discountMapper::toDto), HttpStatus.OK);
    }

    @GetMapping("/type-service/{typeOfServiceId}")
    public ResponseEntity<Page<DiscountResponseDto>> getByTypeOfService(@PathVariable Long typeOfServiceId,
                                                                        Pageable pageable) {
        Page<Discount> discounts = discountService.findByCurrentDateAndTypeOfServiceId
                (DateCreater.getCurrentStartOfDayAsSeconds(), typeOfServiceId, pageable);
        return new ResponseEntity<>(discounts.map(discountMapper::toDto), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<DiscountResponseDto>> getByUserId(@PathVariable Long userId,
                                                                 Pageable pageable) {
        Page<Discount> discounts = discountService.findByCurrentDateAndUserId(
                DateCreater.getCurrentStartOfDayAsSeconds(), userId, pageable);
        return new ResponseEntity<>(discounts.map(discountMapper::toDto), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DiscountResponseDto> create(@RequestBody DiscountRequestDto discountRequestDto) {
        Discount savedDiscount = discountService.save(discountMapper.toModel(discountRequestDto));
        return new ResponseEntity<>(discountMapper.toDto(savedDiscount), HttpStatus.CREATED);
    }

    @DeleteMapping("/{discountId}")
    public ResponseEntity<Void> delete(@PathVariable Long discountId) {
        discountService.deleteById(discountId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
