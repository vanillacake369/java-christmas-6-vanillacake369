package christmas.domain.calendar;

import christmas.domain.user.Day;
import java.util.Arrays;
import java.util.List;

public enum StarredDates {
    THIRD(new Day(3, "일요일")),
    TENTH(new Day(10, "일요일")),
    SEVENTEENTH(new Day(17, "일요일")),
    TWENTY_FOURTH(new Day(24, "일요일")),
    TWENTY_FIFTH(new Day(25, "월요일")),
    THIRTY_FIRST(new Day(31, "일요일"));

    public final Day day;

    StarredDates(Day day) {
        this.day = day;
    }

    public static boolean isStarredDate(int day) {
        List<Integer> starredDates = Arrays.stream(values())
                .map(d -> d.day.day())
                .toList();
        return starredDates.contains(day);
    }
}
