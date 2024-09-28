package hi.place.controller;

import hi.place.model.MainTypeOfService;
import hi.place.service.MainTypeOfServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/main-type-service")
@RequiredArgsConstructor
public class MainTypeOfServiceController {
    private final MainTypeOfServiceService mainTypeOfServiceService;

    @GetMapping
    public ResponseEntity<List<MainTypeOfService>> getAll() {
        return new ResponseEntity<>(mainTypeOfServiceService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<MainTypeOfService>> getAllByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(mainTypeOfServiceService.getAllByUserId(userId), HttpStatus.OK);
    }
}
