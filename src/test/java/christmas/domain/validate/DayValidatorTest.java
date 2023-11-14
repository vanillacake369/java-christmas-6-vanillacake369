package christmas.domain.validate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DayValidatorTest {
    @ParameterizedTest
    @ValueSource(strings = {"1", "15", "31"})
    @DisplayName("1이상 31이하의 날짜 입력에 대해 정상처리합니다.")
    void 날짜입력_해피케이스(String dayInput) {
        // WHEN
        // THEN
        assertDoesNotThrow(() -> DayValidator.validateDay.accept(dayInput));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "32"})
    @DisplayName("1미만 31초과의 날짜 입력에 대해 예외처리합니다.")
    void 날짜입력_언해피케이스(String dayInput) {
        // WHEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> DayValidator.validateDay.accept(dayInput));
        // THEN
        assertTrue(exception.getMessage().startsWith("[ERROR]"));
    }
}