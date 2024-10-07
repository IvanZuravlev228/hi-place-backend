package hi.place.util;

import java.time.LocalDate;
import java.time.ZoneId;

public class DateCreater {
    public static Long getCurrentStartOfDayAsSeconds() {
        return LocalDate.now()
                .atStartOfDay(ZoneId.systemDefault())
                .toEpochSecond();
    }
}
