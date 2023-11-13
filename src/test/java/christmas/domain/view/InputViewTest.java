package christmas.domain.view;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.domain.validate.DayValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputViewTest {

    @ParameterizedTest
    @DisplayName("1이상 31이하의 날짜 입력인 경우 참을 반환합니다.")
    @ValueSource(strings = {"1", "15", "31"})
    void 날짜입력_해피케이스(String dayInput) {
        // GIVEN
        boolean sucess = false;
        // WHEN
        boolean result = InputView.isValidInput(DayValidator.validateDay, sucess, dayInput);
        // THEN
        assertTrue(result);
    }

    @ParameterizedTest
    @DisplayName("1미만, 31초과 날짜 입력인 경우 거짓을 반환합니다.")
    @ValueSource(strings = {"0", "32"})
    void 날짜입력_언해피케이스(String dayInput) {
        // GIVEN
        boolean sucess = false;
        // WHEN
        boolean result = InputView.isValidInput(DayValidator.validateDay, sucess, dayInput);
        // THEN
        assertFalse(result);
    }
}