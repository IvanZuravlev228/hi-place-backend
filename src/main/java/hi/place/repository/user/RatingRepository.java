package hi.place.repository.user;

import hi.place.model.user.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> getAllByUser_Id(Long userId);
}
