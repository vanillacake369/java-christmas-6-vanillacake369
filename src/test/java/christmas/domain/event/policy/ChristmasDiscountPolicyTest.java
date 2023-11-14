package christmas.domain.event.policy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChristmasDiscountPolicyTest {

    @Test
    @DisplayName("1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가합니다.")
    void 크리스마스할인금액_계산() {
        // GIVEN
        int day1 = 1;
        int day25 = 25;

        // WHEN
        Long discountPrice1 = ChristmasDiscountPolicy.getDiscountPrice(day1);
        Long discountPrice25 = ChristmasDiscountPolicy.getDiscountPrice(day25);

        // THEN
        assertEquals(1_000L, discountPrice1);
        assertEquals(3_400L, discountPrice25);
    }
}