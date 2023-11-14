package christmas.domain.user;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public final class DayMapper {
    private final static int YEAR = 2023;
    private final static int MONTH = 12;

    private DayMapper() {
    }

    public static Day createDayByDayInput(int dayInput) {
        String dateByDay = getDateByDay(dayInput);
        return new Day(dayInput, dateByDay);
    }

    static String getDateByDay(int dayInput) {
        LocalDate localDate = LocalDate.of(YEAR, MONTH, dayInput);
        TextStyle textStyle = TextStyle.FULL;
        return localDate.getDayOfWeek().getDisplayName(textStyle, Locale.KOREAN);
    }
}
