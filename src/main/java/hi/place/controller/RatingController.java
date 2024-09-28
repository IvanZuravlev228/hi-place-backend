package hi.place.controller;

import hi.place.dto.rating.RatingRequestDto;
import hi.place.dto.rating.RatingResponseDto;
import hi.place.model.user.Rating;
import hi.place.service.RatingService;
import hi.place.service.mapper.RequestResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rating")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;
    private final RequestResponseMapper<RatingRequestDto, RatingResponseDto, Rating> ratingMapper;

    @GetMapping("/{userId}")
    public ResponseEntity<List<RatingResponseDto>> getAllByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(ratingService.getAllByUserId(userId)
                .stream()
                .map(ratingMapper::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Boolean> addRating(@RequestBody RatingRequestDto ratingDto) {
        return new ResponseEntity<>(
                ratingService.add(ratingMapper.toModel(ratingDto)).getId() > 0,
                HttpStatus.CREATED);
    }
}
