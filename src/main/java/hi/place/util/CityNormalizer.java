package hi.place.util;

import java.util.HashMap;
import java.util.Map;

public class CityNormalizer {
    private static final Map<String, String> cityNames = new HashMap<>();

    static {
        cityNames.put("Kyiv", "Kyiv");
        cityNames.put("Київ", "Kyiv");
        cityNames.put("Kiev", "Kyiv");

        cityNames.put("Lviv", "Lviv");
        cityNames.put("Львів", "Lviv");
        cityNames.put("Lvov", "Lviv");

        cityNames.put("Kharkiv", "Kharkiv");
        cityNames.put("Харків", "Kharkiv");
        cityNames.put("Kharkov", "Kharkiv");

        cityNames.put("Odesa", "Odesa");
        cityNames.put("Одеса", "Odesa");
        cityNames.put("Odessa", "Odesa");

        cityNames.put("Dnipro", "Dnipro");
        cityNames.put("Дніпро", "Dnipro");
        cityNames.put("Dnepr", "Dnipro");

        cityNames.put("Zaporizhzhia", "Zaporizhzhia");
        cityNames.put("Запоріжжя", "Zaporizhzhia");
        cityNames.put("Zaporozhye", "Zaporizhzhia");

        cityNames.put("Donetsk", "Donetsk");
        cityNames.put("Донецьк", "Donetsk");

        cityNames.put("Luhansk", "Luhansk");
        cityNames.put("Луганськ", "Luhansk");
        cityNames.put("Lugansk", "Luhansk");

        cityNames.put("Vinnytsia", "Vinnytsia");
        cityNames.put("Вінниця", "Vinnytsia");
        cityNames.put("Vinnitsa", "Vinnytsia");

        cityNames.put("Simferopol", "Simferopol");
        cityNames.put("Сімферополь", "Simferopol");

        cityNames.put("Sevastopol", "Sevastopol");
        cityNames.put("Севастополь", "Sevastopol");
    }

    public static String normalizeCityName(String cityName) {
        return cityNames.getOrDefault(cityName, cityName);
    }
}
