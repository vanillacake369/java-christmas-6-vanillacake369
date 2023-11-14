package christmas.domain.user;

import christmas.domain.validate.DayValidator;

public record Day(int day, String date) {
    public Day {
        validateDay(day, date);
    }

    public static void validateDay(int day, String date) {
        // 날짜 검증
        DayValidator.validateDay.accept(Integer.toString(day));
        // 요일 검증
        DayValidator.validateDate.accept(date);
    }
}
