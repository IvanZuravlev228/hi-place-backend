package hi.place.controller;

import hi.place.dto.ServiceItemResponseDto;
import hi.place.model.ServiceItem;
import hi.place.service.ServiceItemService;
import hi.place.service.mapper.ServiceItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/service")
@RequiredArgsConstructor
public class ServiceItemController {
    private final ServiceItemService serviceItemService;
    private final ServiceItemMapper serviceItemMapper;

    @GetMapping("/{typeServiceId}")
    public ResponseEntity<List<ServiceItemResponseDto>> getByTypeServiceId(@PathVariable Long typeServiceId) {
        return new ResponseEntity<>(serviceItemService.getByTypeOfServiceId(typeServiceId)
                .stream()
                .map(serviceItemMapper::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }
}
