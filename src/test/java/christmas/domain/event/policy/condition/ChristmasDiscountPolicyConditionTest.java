package christmas.domain.event.policy.condition;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ChristmasDiscountPolicyConditionTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 15, 25})
    @DisplayName("2023.12.1 ~ 2023.12.25 기간 내라면 크리스마스 디데이 할인 적용")
    void 크리스마스할인_해피케이스(int day) {
        // WHEN
        boolean verifyCondition = ChristmasDiscountPolicyCondition.verifyCondition(day);
        // THEN
        assertTrue(verifyCondition);
    }

    @ParameterizedTest
    @ValueSource(ints = {26, 31})
    @DisplayName("2023.12.1 ~ 2023.12.25 기간이 아니라면 크리스마스 디데이 할인 미적용")
    void 크리스마스할인_언해피케이스(int day) {
        // WHEN
        boolean verifyCondition = ChristmasDiscountPolicyCondition.verifyCondition(day);
        // THEN
        assertFalse(verifyCondition);
    }
}