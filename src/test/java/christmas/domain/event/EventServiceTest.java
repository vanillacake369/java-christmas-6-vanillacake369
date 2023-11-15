package christmas.domain.event;

import static christmas.domain.menu.Menu.BARBECUE_RIBS;
import static christmas.domain.menu.Menu.CHOCOLATE_CAKE;
import static christmas.domain.menu.Menu.T_BONE_STEAK;
import static christmas.domain.menu.Menu.ZERO_COKE;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.domain.event.policy.ChristmasDiscountPolicy;
import christmas.domain.event.policy.EventPolicy;
import christmas.domain.event.policy.GiftEventPolicy;
import christmas.domain.event.policy.SpecialDiscountPolicy;
import christmas.domain.event.policy.WeekDayDiscountPolicy;
import christmas.domain.event.policy.WeekEndDiscountPolicy;
import christmas.domain.menu.Menu;
import christmas.domain.user.Day;
import christmas.domain.user.Order;
import christmas.domain.user.User;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EventServiceTest {
    public static Stream<Arguments> createUserOfWeekDay() {
        Day day = new Day(3, "일요일");
        HashMap<Menu, Integer> orderMenus = new HashMap<>();
        orderMenus.put(T_BONE_STEAK, 1);
        orderMenus.put(BARBECUE_RIBS, 1);
        orderMenus.put(CHOCOLATE_CAKE, 2);
        orderMenus.put(ZERO_COKE, 1);
        Order order = new Order(orderMenus);
        User user = new User(day, order);
        return Stream.of(
                Arguments.of(user)
        );
    }

    public static Stream<Arguments> createUserOfWeekEnd() {
        Day day = new Day(1, "금요일");
        HashMap<Menu, Integer> orderMenus = new HashMap<>();
        orderMenus.put(T_BONE_STEAK, 2);
        orderMenus.put(BARBECUE_RIBS, 1);
        orderMenus.put(CHOCOLATE_CAKE, 2);
        orderMenus.put(ZERO_COKE, 1);
        Order order = new Order(orderMenus);
        User user = new User(day, order);
        return Stream.of(
                Arguments.of(user)
        );
    }

    @ParameterizedTest
    @MethodSource("createUserOfWeekDay")
    @DisplayName("주문 메뉴에 따라 평일할인을 포함한 할인정책을 선택/배치하고, 적용합니다.")
    void 평일할인_포함_할인정책_배치_적용(User user) {
        // GIVEN
        EventService eventService = new EventService(user);
        EventResultDTO eventResultDTO = EventResultDTO.initEventResultDTO(user.getPriceSum());

        // WHEN
        eventService.applyEvents(eventResultDTO);
        HashMap<EventPolicy, Long> appliedEventPrice = eventResultDTO.getAppliedEventPrice();
        Set<EventPolicy> eventPolicies = appliedEventPrice.keySet();
        Collection<Long> eventPrices = appliedEventPrice.values();

        // THEN
        assertTrue(eventPolicies.stream().anyMatch(ChristmasDiscountPolicy.class::isInstance));
        assertTrue(eventPolicies.stream().anyMatch(WeekDayDiscountPolicy.class::isInstance));
        assertTrue(eventPolicies.stream().anyMatch(SpecialDiscountPolicy.class::isInstance));
        assertTrue(eventPolicies.stream().anyMatch(GiftEventPolicy.class::isInstance));
        assertTrue(eventPrices.contains(1_200L));
        assertTrue(eventPrices.contains(4_046L));
        assertTrue(eventPrices.contains(1_000L));
        assertTrue(eventPrices.contains(25_000L));
    }

    @ParameterizedTest
    @MethodSource("createUserOfWeekEnd")
    @DisplayName("주문 메뉴에 따라 주말할인을 포함한 할인정책을 선택/배치하고, 적용합니다.")
    void 주말할인_포함_할인정책_배치_적용(User user) {
        // GIVEN
        EventService eventService = new EventService(user);
        EventResultDTO eventResultDTO = EventResultDTO.initEventResultDTO(user.getPriceSum());

        // WHEN
        eventService.applyEvents(eventResultDTO);
        HashMap<EventPolicy, Long> appliedEventPrice = eventResultDTO.getAppliedEventPrice();
        Set<EventPolicy> eventPolicies = appliedEventPrice.keySet();
        Collection<Long> eventPrices = appliedEventPrice.values();

        // THEN
        System.out.println(eventPolicies);
        assertTrue(eventPolicies.stream().anyMatch(ChristmasDiscountPolicy.class::isInstance));
        assertTrue(eventPolicies.stream().anyMatch(WeekEndDiscountPolicy.class::isInstance));
        assertTrue(eventPolicies.stream().anyMatch(GiftEventPolicy.class::isInstance));
        assertTrue(eventPrices.contains(1_000L));
        assertTrue(eventPrices.contains(2_023L * 3));
        assertTrue(eventPrices.contains(25_000L));
    }
}