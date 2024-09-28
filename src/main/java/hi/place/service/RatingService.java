package hi.place.service;

import hi.place.model.user.Rating;

import java.util.List;

public interface RatingService {
    Rating add(Rating rating);

    List<Rating> getAllByUserId(Long userId);
}
