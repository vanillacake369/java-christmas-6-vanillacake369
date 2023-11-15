package christmas.domain.event.policy;

import static christmas.domain.menu.Menu.BARBECUE_RIBS;
import static christmas.domain.menu.Menu.CHOCOLATE_CAKE;
import static christmas.domain.menu.Menu.T_BONE_STEAK;
import static christmas.domain.menu.Menu.ZERO_COKE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.event.EventResultDTO;
import christmas.domain.event.batch.EventBatch;
import christmas.domain.event.policy.discount.ChristmasDiscountPolicy;
import christmas.domain.menu.Menu;
import christmas.domain.user.Day;
import christmas.domain.user.Order;
import christmas.domain.user.User;
import java.util.HashMap;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ChristmasEventPolicyTest {
    private ChristmasDiscountPolicy christmasDiscountPolicy;

    public static Stream<Arguments> createUserAndEventResultDto() {
        EventResultDTO eventResultDto = new EventResultDTO();
        return Stream.of(
                Arguments.of(eventResultDto)
        );
    }

    private static User createUser() {
        Day day = new Day(3, "일요일");
        HashMap<Menu, Integer> orderMenus = new HashMap<>();
        orderMenus.put(T_BONE_STEAK, 1);
        orderMenus.put(BARBECUE_RIBS, 1);
        orderMenus.put(CHOCOLATE_CAKE, 2);
        orderMenus.put(ZERO_COKE, 1);
        Order order = new Order(orderMenus);
        return new User(day, order);
    }

    @BeforeEach
    void setUp() {
        User user = createUser();
        EventBatch eventBatch = new EventBatch(user);
        christmasDiscountPolicy = new ChristmasDiscountPolicy(user, eventBatch);
    }

    @Test
    @DisplayName("1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가합니다.")
    void 크리스마스할인금액_계산() {
        // GIVEN
        Day day1 = new Day(1, "금요일");
        Day day25 = new Day(25, "월요일");

        // WHEN
        Long discountPrice1 = christmasDiscountPolicy.getDiscountPrice(day1);
        Long discountPrice25 = christmasDiscountPolicy.getDiscountPrice(day25);

        // THEN
        assertEquals(1_000L, discountPrice1);
        assertEquals(3_400L, discountPrice25);
    }

    @ParameterizedTest
    @MethodSource("createUserAndEventResultDto")
    @DisplayName("크리스마스 할인 조건에 따라 크리스마스 할인을 실행합니다.")
    void 크리스마스할인_실행(EventResultDTO resultDTO) {
        // WHEN
        christmasDiscountPolicy.applyEvent(resultDTO);

        // THEN
        assertEquals(resultDTO.getAppliedEventPolicies().get(christmasDiscountPolicy), 1_200L);
    }
}