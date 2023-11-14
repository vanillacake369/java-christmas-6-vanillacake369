package christmas.domain.user;

import christmas.domain.validate.DayValidator;

public record Day(int day, String date) {
    public Day {
        // 날짜 검증
        DayValidator.validateDay.accept(Integer.toString(day));
        // 요일 검증

    }
}
