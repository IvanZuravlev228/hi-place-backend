package hi.place.controller;

import hi.place.dto.address.AddressRequestDto;
import hi.place.dto.address.AddressResponseDto;
import hi.place.exception.InvalidOwnerException;
import hi.place.model.address.Address;
import hi.place.service.AddressService;
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
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;
    private final UserService userService;
    private final RequestResponseMapper<AddressRequestDto, AddressResponseDto, Address> addressMapper;

    @PostMapping
    public ResponseEntity<AddressResponseDto> saveNewAddress(@RequestBody AddressRequestDto addressRequestDto,
                                                             Authentication authentication) {
        checkOwner(addressRequestDto.getUserId(), authentication);
        return new ResponseEntity<>(addressMapper.toDto(addressService.save(addressMapper.toModel(addressRequestDto))),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDto> getAddressById(@PathVariable Long id) {
        return new ResponseEntity<>(addressMapper.toDto(addressService.getById(id)), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AddressResponseDto>> getAllByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(addressService.getByUserId(userId)
                .stream()
                .map(addressMapper::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddressById(@PathVariable Long addressId,
                                                  Authentication authentication) {
        Address addressForDelete = addressService.getById(addressId);
        checkOwner(addressForDelete.getUser().getId(), authentication);

        addressService.deleteById(addressId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/near")
    public ResponseEntity<List<AddressResponseDto>> getAll(@RequestParam Double lat,
                                                           @RequestParam Double lon) {
        return new ResponseEntity<>(addressService.getAllNearAddress(lat, lon)
                .stream()
                .map(addressMapper::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/cities")
    public ResponseEntity<List<String>> getAllCities() {
        return new ResponseEntity<>(addressService.getAllCities(), HttpStatus.OK);
    }

    private void checkOwner(Long userId, Authentication requestUserAuthentication) {
        if (requestUserAuthentication != null) {
            String userEmail = requestUserAuthentication.getName();
            if (!userId.equals(userService.getByEmail(userEmail).getId())) {
                throw new InvalidOwnerException("You cannot change the data of another profile");
            }
        }
    }
}
