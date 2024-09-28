package hi.place.service.impl.user;

import hi.place.model.user.Rating;
import hi.place.repository.user.RatingRepository;
import hi.place.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;

    @Override
    public Rating add(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllByUserId(Long userId) {
        return ratingRepository.getAllByUser_Id(userId);
    }
}
