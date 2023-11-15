package christmas.domain.user;

import static christmas.domain.calendar.EventTime.MONTH;
import static christmas.domain.calendar.EventTime.YEAR;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public final class DayMapper {
    private DayMapper() {
    }

    public static Day createDayByDayInput(int dayInput) {
        String dateByDay = getDateByDay(dayInput);
        return new Day(dayInput, dateByDay);
    }

    static String getDateByDay(int dayInput) {
        LocalDate localDate = LocalDate.of(YEAR.time, MONTH.time, dayInput);
        TextStyle textStyle = TextStyle.FULL;
        return localDate.getDayOfWeek().getDisplayName(textStyle, Locale.KOREAN);
    }
}
