package hi.place.service;

import hi.place.model.user.Review;

import java.util.List;

public interface ReviewService {
    Review add(Review review);

    List<Review> getAllByUserId(Long userId);

    List<Review> getAllByUserIdAndClientId(Long userId, Long clientId);
}
