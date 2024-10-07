package hi.place.repository.user;

import hi.place.model.user.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> getAllByUser_Id(Long userId);

    List<Review> findAllByUser_IdAndClient_Id(Long userId, Long clientId);
}
