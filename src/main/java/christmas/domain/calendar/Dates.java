package christmas.domain.calendar;

import java.util.Arrays;
import java.util.Objects;

public enum Dates {
    SUNDAY("일요일", 0),
    MONDAY("월요일", 1),
    TUESDAY("화요일", 2),
    WEDNESDAY("수요일", 3),
    THURSDAY("목요일", 4),
    FRIDAY("금요일", 5),
    SATURDAY("토요일", 6);

    final String dateKor;
    final int dateSeq;

    Dates(String dateKor, int dateSeq) {
        this.dateKor = dateKor;
        this.dateSeq = dateSeq;
    }

    public static int getDateSeqByDateKor(String dateKor) {
        return Objects.requireNonNull(Arrays.stream(values())
                .filter(dates -> dates.dateKor.equals(dateKor))
                .findFirst().orElse(null)).dateSeq;
    }

    public String getDateKor() {
        return dateKor;
    }
}
