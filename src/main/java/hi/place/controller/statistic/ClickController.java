package hi.place.controller.statistic;

import hi.place.service.ClickService;
import hi.place.util.DateCreater;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class ClickController {
    private final ClickService clickService;

    @PostMapping("/user/{userId}/click/{clickTo}")
    public void clickedToPhone(@PathVariable String clickTo,
                               @PathVariable Long userId) {
        clickService.clicked(clickTo, DateCreater.getCurrentStartOfDayAsSeconds(), userId);
    }
}
