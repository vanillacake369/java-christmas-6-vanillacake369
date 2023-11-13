package christmas.domain.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DayMapperTest {

    @ParameterizedTest
    @ValueSource(ints = 9)
    @DisplayName("입력일로부터 요일을 계산합니다")
    void 입력일로부터_요일계산(int dayInput) {
        // WHEN
        String dateByDay = DayMapper.getDateByDay(dayInput);

        // THEN
        assertEquals("토요일", dateByDay);
    }

    @ParameterizedTest
    @ValueSource(ints = 9)
    @DisplayName("입력일에 대한 방문 날짜를 생성합니다.")
    void 방문날짜생성(int dayInput) {
        // WHEN
        Day day = DayMapper.createDayByDayInput(dayInput);
        // THEN
        assertEquals("토요일", day.date());
        assertEquals(dayInput, day.day());
    }
}