package hi.place.service;

public interface ClickService {
    void clicked(String clickTo, Long date, Long userId);

    Integer countClicks(String clickType, Long userId, Long start, Long end);
}
