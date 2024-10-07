package hi.place.service.impl.user;

import hi.place.model.user.Review;
import hi.place.repository.user.ReviewRepository;
import hi.place.service.ReviewService;
import hi.place.util.DateCreater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public Review add(Review rating) {
        rating.setAddedDate(DateCreater.getCurrentStartOfDayAsSeconds());
        return reviewRepository.save(rating);
    }

    @Override
    public List<Review> getAllByUserId(Long userId) {
        return reviewRepository.getAllByUser_Id(userId);
    }

    @Override
    public List<Review> getAllByUserIdAndClientId(Long userId, Long clientId) {
        return reviewRepository.findAllByUser_IdAndClient_Id(userId, clientId);
    }
}
