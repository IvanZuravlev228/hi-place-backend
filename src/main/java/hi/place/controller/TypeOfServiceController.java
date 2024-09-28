package hi.place.controller;

import hi.place.dto.TypeOfServiceCountDto;
import hi.place.dto.TypeOfServiceResponseDto;
import hi.place.model.TypeOfService;
import hi.place.service.TypeOfServiceService;
import hi.place.service.mapper.TypeOfServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/type-service")
@RequiredArgsConstructor
public class TypeOfServiceController {
    private final TypeOfServiceService typeOfServiceService;
    private final TypeOfServiceMapper typeOfServiceMapper;

    @GetMapping("/{mainTypeId}")
    public ResponseEntity<List<TypeOfServiceResponseDto>> getByMainTypeId(@PathVariable Long mainTypeId) {
        return new ResponseEntity<>(typeOfServiceService.getByMainTypeId(mainTypeId)
                .stream()
                .map(typeOfServiceMapper::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TypeOfServiceCountDto>> getAllTypeOfServiceCount(@PathVariable Long userId) {
        return new ResponseEntity<>(typeOfServiceService.getTypeOfServiceCountByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/discounts")
    public ResponseEntity<List<TypeOfServiceResponseDto>> getAllWithDiscount() {
        return new ResponseEntity<>(typeOfServiceService.getAllWithDiscount(Instant.now().getEpochSecond())
                .stream()
                .map(typeOfServiceMapper::toDto)
                .toList(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TypeOfServiceResponseDto>> getAll() {
        return new ResponseEntity<>(typeOfServiceService.getAll()
                .stream()
                .map(typeOfServiceMapper::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }
}
