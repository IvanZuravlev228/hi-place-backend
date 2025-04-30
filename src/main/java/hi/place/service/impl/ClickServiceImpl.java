package hi.place.service.impl;

import hi.place.model.Click;
import hi.place.repository.ClickRepository;
import hi.place.service.ClickService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClickServiceImpl implements ClickService {
    private final ClickRepository clickRepository;

    @Override
    public void clicked(String clickType, Long date, Long userId) {
        Click click = new Click();
        setClickType(click, clickType, date);
        saveClick(click, userId);
    }

    @Override
    public Integer countClicks(String clickType, Long userId, Long start, Long end) {
        return switch (clickType.toLowerCase()) {
            case "phone" -> clickRepository.countClicksByPhoneAndPeriod(userId, start, end);
            case "instagram" -> clickRepository.countClicksByInstagramAndPeriod(userId, start, end);
            case "tiktok" -> clickRepository.countClicksByTiktokAndPeriod(userId, start, end);
            case "telegram" -> clickRepository.countClicksByTelegramAndPeriod(userId, start, end);
            default -> throw new IllegalArgumentException("Unknown click type: " + clickType);
        };
    }

    private void setClickType(Click click, String clickType, Long date) {
        switch (clickType.toLowerCase()) {
            case "phone":
                click.setClickedToPhone(date);
                break;
            case "instagram":
                click.setClickedToInstagram(date);
                break;
            case "tiktok":
                click.setClickedToTiktok(date);
                break;
            case "telegram":
                click.setClickedToTelegram(date);
                break;
            default:
                throw new IllegalArgumentException("Unknown click type: " + clickType);
        }
    }

    private void saveClick(Click click, Long userId) {
        click.setUserId(userId);
        clickRepository.save(click);
    }
}
