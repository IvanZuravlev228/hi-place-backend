package hi.place.controller;

import hi.place.dto.price.PriceProfileResponseDto;
import hi.place.dto.price.PriceRequestDto;
import hi.place.dto.price.PriceResponseDto;
import hi.place.exception.InvalidOwnerException;
import hi.place.model.user.Price;
import hi.place.service.PriceService;
import hi.place.service.UserService;
import hi.place.service.mapper.RequestResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/price")
@RequiredArgsConstructor
public class PriceController {
    private final PriceService priceService;
    private final UserService userService;
    private final RequestResponseMapper<PriceRequestDto, PriceResponseDto, Price> priceMapper;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PriceResponseDto>> getAllByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(priceService.getAllByUserId(userId)
                .stream()
                .map(priceMapper::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Boolean> addPriceToUser(@RequestBody List<PriceRequestDto> price, Authentication authentication) {
        checkOwner(price.get(0).getUserId(), authentication);

        return new ResponseEntity<>(!priceService.addToUser(price.stream().map(priceMapper::toModel).toList()).isEmpty(),
                HttpStatus.CREATED);
    }

    @PutMapping("/{prevPriceId}")
    public ResponseEntity<Boolean> updatePrice(@RequestBody PriceRequestDto price,
                                               @PathVariable Long prevPriceId,
                                               Authentication authentication) {
        checkOwner(price.getUserId(), authentication);

        return new ResponseEntity<>(priceService.update(priceMapper.toModel(price), prevPriceId).getId() > 0,
                HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/type/{typeOfServiceId}")
    public ResponseEntity<List<PriceProfileResponseDto>> getAllByTypeOfServiceId(@PathVariable Long userId,
                                                                                 @PathVariable Long typeOfServiceId) {
        return new ResponseEntity<>(priceService.getAllByTypeOfServiceIdAndUserId(typeOfServiceId, userId), HttpStatus.OK);
    }

    @GetMapping("/type/{typeId}/user/{userId}")
    public ResponseEntity<List<PriceProfileResponseDto>> getAllServiceItemsWithoutPrice(@PathVariable Long typeId,
                                                                                        @PathVariable Long userId) {
        return new ResponseEntity<>(priceService.getAllServiceItemsWithoutPrice(typeId, userId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrice(@PathVariable Long id, Authentication authentication) {
        Price priceForDelete = priceService.getById(id);
        checkOwner(priceForDelete.getUser().getId(), authentication);

        priceService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void checkOwner(Long userId, Authentication requestUserAuthentication) {
        String userEmail = requestUserAuthentication.getName();
        if (!userId.equals(userService.getByEmail(userEmail).getId())) {
            throw new InvalidOwnerException("You cannot change the data of another profile");
        }
    }
}
