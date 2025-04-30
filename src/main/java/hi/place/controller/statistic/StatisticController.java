package hi.place.controller.statistic;

import hi.place.dto.price.AvgPriceByServiceItemDTO;
import hi.place.dto.user.UsersCountDto;
import hi.place.model.user.User;
import hi.place.service.ClickService;
import hi.place.service.PriceService;
import hi.place.service.UserService;
import hi.place.service.VisitService;
import hi.place.util.DateCreater;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticController {
    private final ClickService clickService;
    private final VisitService visitService;
    private final UserService userService;
    private final PriceService priceService;

    @GetMapping("/click/{clickType}")
    public ResponseEntity<Integer> countClicksToInstagram(@PathVariable String clickType,
                                                          @RequestParam Long userId,
                                                          @RequestParam Long startTime,
                                                          @RequestParam Long endTime) {
        return new ResponseEntity<>(clickService.countClicks(clickType, userId, startTime, endTime), HttpStatus.OK);
    }

    @GetMapping("/visit")
    public ResponseEntity<Integer> countVisits(@RequestParam Long userId,
                                               @RequestParam Long startTime,
                                               @RequestParam Long endTime) {
        return new ResponseEntity<>(visitService.getVisitCount(userId, startTime, endTime), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/list-position")
    public ResponseEntity<Long> userPosition(@PathVariable Long userId) {
        List<User> users = userService.getAllOrderByAvgRating();
        long index = users.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .map(users::indexOf)
                .orElse(-1) + 1;

        return new ResponseEntity<>(index, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/avg-price")
    public ResponseEntity<List<AvgPriceByServiceItemDTO>> getAvgPriceByServiceItem(@PathVariable Long userId) {
        return new ResponseEntity<>(priceService.getAveragePriceByServiceItemForUser(userId), HttpStatus.OK);
    }

    @GetMapping("/count/users")
    public ResponseEntity<UsersCountDto> countUsers() {
        Long salonUsers = userService.getCountUsersByType(User.UserType.SALON);
        Long masterUsers = userService.getCountUsersByType(User.UserType.MASTER);
        return new ResponseEntity<>(new UsersCountDto(salonUsers, masterUsers), HttpStatus.OK);
    }

}
