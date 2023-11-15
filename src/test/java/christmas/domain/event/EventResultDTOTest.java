package christmas.domain.event;

import static christmas.domain.menu.Menu.BARBECUE_RIBS;
import static christmas.domain.menu.Menu.CHOCOLATE_CAKE;
import static christmas.domain.menu.Menu.T_BONE_STEAK;
import static christmas.domain.menu.Menu.ZERO_COKE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.event.batch.EventBatch;
import christmas.domain.event.policy.discount.ChristmasDiscountPolicy;
import christmas.domain.event.policy.discount.DiscountPolicy;
import christmas.domain.event.policy.discount.SpecialDiscountPolicy;
import christmas.domain.event.policy.discount.WeekDayDiscountPolicy;
import christmas.domain.event.policy.event.EventPolicy;
import christmas.domain.event.policy.event.GiftEventPolicy;
import christmas.domain.menu.Menu;
import christmas.domain.user.Day;
import christmas.domain.user.Order;
import christmas.domain.user.User;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventResultDTOTest {
    private final User user = createUser();
    private final EventBatch eventBatch = new EventBatch(user);
    private final DiscountPolicy christmasDiscountPolicy = new ChristmasDiscountPolicy(user, eventBatch);
    private final DiscountPolicy weekDayDiscountPolicy = new WeekDayDiscountPolicy(user, eventBatch);
    private final DiscountPolicy specialDiscountPolicy = new SpecialDiscountPolicy(user, eventBatch);
    private final EventPolicy giftEventPolicy = new GiftEventPolicy(user, eventBatch);
    private final EventResultDTO resultDTO = new EventResultDTO();

    User createUser() {
        Day day = new Day(3, "일요일");
        HashMap<Menu, Integer> orderMenus = new HashMap<>();
        orderMenus.put(T_BONE_STEAK, 1);
        orderMenus.put(BARBECUE_RIBS, 1);
        orderMenus.put(CHOCOLATE_CAKE, 2);
        orderMenus.put(ZERO_COKE, 1);
        Order order = new Order(orderMenus);
        return new User(day, order);
    }

    private void updateAppliedEventPrice() {
        resultDTO.putAppliedEventPolicy(christmasDiscountPolicy, 1_200L);
        resultDTO.putAppliedEventPolicy(weekDayDiscountPolicy, 4_046L);
        resultDTO.putAppliedEventPolicy(specialDiscountPolicy, 1_000L);
        resultDTO.putAppliedEventPolicy(giftEventPolicy, 25_000L);
    }

    @Test
    @DisplayName("모든 이벤트에 대한 총 혜택 금액을 계산합니다.")
    void 총혜택금액_계산_검증() {
        // GIVEN
        updateAppliedEventPrice();

        // WHEN
        resultDTO.updateBenefitPriceSum();

        // THEN
        Long benefitPriceSum = resultDTO.getBenefitPriceSum();
        assertEquals(benefitPriceSum, 31246);
    }

    @Test
    @DisplayName("할인 이벤트에 대해서만 총 할인 금액을 계산합니다.")
    void 할인총금액_계산_검증() {
        // GIVEN
        updateAppliedEventPrice();

        // WHEN
        resultDTO.updateDiscountPriceSum();

        // THEN
        Long discountPriceSum = resultDTO.getDiscountPriceSum();
        assertEquals(discountPriceSum, 6246);
    }
}