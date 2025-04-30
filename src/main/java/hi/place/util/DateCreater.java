package hi.place.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class DateCreater {
    public static Long getCurrentStartOfDayAsSeconds() {
        return LocalDate.now()
                .atStartOfDay(ZoneId.systemDefault())
                .toEpochSecond();
    }

    public static Long getCurrentTimeAsSeconds() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    }
}
