package hi.place.controller;

import hi.place.dto.Response;
import hi.place.dto.rating.ReviewRequestDto;
import hi.place.dto.rating.ReviewResponseDto;
import hi.place.model.user.Client;
import hi.place.model.user.Review;
import hi.place.service.ClientService;
import hi.place.service.EmailService;
import hi.place.service.ReviewService;
import hi.place.service.mapper.RequestResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ClientService clientService;
    private final EmailService emailService;
    private final RequestResponseMapper<ReviewRequestDto, ReviewResponseDto, Review> reviewMapper;

    @GetMapping("/{userId}")
    public ResponseEntity<List<ReviewResponseDto>> getAllByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(reviewService.getAllByUserId(userId)
                .stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> addReview(@RequestBody ReviewRequestDto reviewDto) {
        Optional<Client> clientOptional = clientService.getByEmail(reviewDto.getClientEmail());

        if (clientOptional.isEmpty()) {
            Client newClient = clientService.add(new Client(reviewDto.getClientName(), reviewDto.getClientEmail()));
            reviewDto.setClientId(newClient.getId());
            reviewService.add(reviewMapper.toModel(reviewDto));
            emailService.sendSuccessReview(newClient.getId());
            return buildResponse(HttpStatus.CREATED, "Review was saved successfully");
        }

        Client client = clientOptional.get();
        List<Review> reviews = reviewService.getAllByUserIdAndClientId(reviewDto.getUserId(), client.getId());

        if (!reviews.isEmpty()) {
            return buildResponse(HttpStatus.CONFLICT, "Review already exists");
        }

        reviewDto.setClientId(client.getId());
        reviewService.add(reviewMapper.toModel(reviewDto));
        emailService.sendSuccessReview(client.getId());
        return buildResponse(HttpStatus.OK, "Review was saved successfully");
    }

    private ResponseEntity<Response> buildResponse(HttpStatus status, String message) {
        Response response = new Response(status.value(), message);
        return new ResponseEntity<>(response, status);
    }
}
