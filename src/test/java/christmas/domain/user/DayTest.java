package christmas.domain.user;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DayTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 15, 31})
    @DisplayName("1이상 31이하의 날짜 입력에 대해 정상처리합니다.")
    void 날짜입력_해피케이스(int dayInput) {
        // WHEN
        // THEN
        assertDoesNotThrow(() -> new Day(dayInput, "월요일"));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 32})
    @DisplayName("1미만 31초과의 날짜 입력에 대해 예외처리합니다.")
    void 날짜입력_언해피케이스(int dayInput) {
        // WHEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Day(dayInput, "월요일"));
        // THEN
        assertTrue(exception.getMessage().startsWith("[ERROR]"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"금요일"})
    @DisplayName("유효한 날짜 입력에 대해 정상처리합니다.")
    void 요일입력_해피케이스(String dateInput) {
        // WHEN
        // THEN
        assertDoesNotThrow(() -> new Day(1, dateInput));
    }

    @ParameterizedTest
    @ValueSource(strings = {"월요일"})
    @DisplayName("무효한 날짜 입력에 대해 예외처리합니다.")
    void 요일입력_언해피케이스(String dateInput) {
        // WHEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Day(1, dateInput));
        // THEN
        assertTrue(exception.getMessage().startsWith("[ERROR]"));
    }
}