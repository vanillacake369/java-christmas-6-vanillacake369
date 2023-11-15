package christmas.domain.event;

import static christmas.domain.menu.Menu.TAPAS;
import static christmas.domain.menu.Menu.T_BONE_STEAK;
import static christmas.domain.menu.Menu.ZERO_COKE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.domain.menu.Menu;
import christmas.domain.user.Day;
import christmas.domain.user.Order;
import christmas.domain.user.User;
import java.util.HashMap;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EventJoinableCheckerTest {
    public static Stream<Arguments> createPriceHappyCase() {
        Day day = new Day(3, "일요일");
        HashMap<Menu, Integer> orderMenus = new HashMap<>();
        orderMenus.put(T_BONE_STEAK, 1);
        Order order = new Order(orderMenus);
        User user = new User(day, order);

        return Stream.of(
                Arguments.of(user)
        );
    }

    public static Stream<Arguments> createPriceUnhappyCase() {
        Day day = new Day(3, "일요일");
        HashMap<Menu, Integer> orderMenus = new HashMap<>();
        orderMenus.put(TAPAS, 1);
        Order order = new Order(orderMenus);
        User user = new User(day, order);

        return Stream.of(
                Arguments.of(user)
        );
    }

    public static Stream<Arguments> createOrderHappyCase() {
        Day day = new Day(3, "일요일");
        HashMap<Menu, Integer> orderMenus = new HashMap<>();
        orderMenus.put(T_BONE_STEAK, 1);
        orderMenus.put(ZERO_COKE, 1);
        Order order = new Order(orderMenus);
        User user = new User(day, order);

        return Stream.of(
                Arguments.of(user)
        );
    }

    public static Stream<Arguments> createOrderUnhappyCase() {
        Day day = new Day(3, "일요일");
        HashMap<Menu, Integer> orderMenus = new HashMap<>();
        orderMenus.put(ZERO_COKE, 1);
        Order order = new Order(orderMenus);
        User user = new User(day, order);

        return Stream.of(
                Arguments.of(user)
        );
    }

    public static Stream<Arguments> createOrderCountHappyCase() {
        Day day = new Day(3, "일요일");
        HashMap<Menu, Integer> orderMenus = new HashMap<>();
        orderMenus.put(T_BONE_STEAK, 20);
        Order order = new Order(orderMenus);
        User user = new User(day, order);

        return Stream.of(
                Arguments.of(user)
        );
    }

    public static Stream<Arguments> createOrderCountUnhappyCase() {
        Day day = new Day(3, "일요일");
        HashMap<Menu, Integer> orderMenus = new HashMap<>();
        orderMenus.put(T_BONE_STEAK, 21);
        Order order = new Order(orderMenus);
        User user = new User(day, order);

        return Stream.of(
                Arguments.of(user)
        );
    }

    @ParameterizedTest
    @MethodSource("createPriceHappyCase")
    @DisplayName("총주문 금액 10,000원 이상인 경우, 주문할 수 있습니다.")
    void 총주문금액_해피케이스(User user) {
        // WHEN
        boolean verifyLowerBound = EventJoinableChecker.verifyLowerBound(user);

        // THEN
        assertTrue(verifyLowerBound);
    }

    @ParameterizedTest
    @MethodSource("createPriceUnhappyCase")
    @DisplayName("총주문 금액 10,000원 미만인 경우, 주문할 수 없습니다.")
    void 총주문금액_언해피케이스(User user) {
        // WHEN
        boolean verifyLowerBound = EventJoinableChecker.verifyLowerBound(user);

        // THEN
        assertFalse(verifyLowerBound);
    }

    @ParameterizedTest
    @MethodSource("createOrderHappyCase")
    @DisplayName("음료를 포함한 다른 메뉴 주문 시, 주문할 수 있습니다.")
    void 주문메뉴_해피케이스(User user) {
        // WHEN
        boolean verifyOrderMenu = EventJoinableChecker.verifyOrderMenu(user);

        // THEN
        assertTrue(verifyOrderMenu);
    }

    @ParameterizedTest
    @MethodSource("createOrderUnhappyCase")
    @DisplayName("음료만 주문 시, 주문할 수 없습니다.")
    void 주문메뉴_언해피케이스(User user) {
        // WHEN
        boolean verifyOrderMenu = EventJoinableChecker.verifyOrderMenu(user);

        // THEN
        assertFalse(verifyOrderMenu);
    }

    @ParameterizedTest
    @MethodSource("createOrderCountHappyCase")
    @DisplayName("메뉴는 최대 20개까지만 주문할 수 있습니다.")
    void 주문개수_해피케이스(User user) {
        // WHEN
        boolean verifyOrderCountLimit = EventJoinableChecker.verifyOrderCountLimit(user);

        // THEN
        assertTrue(verifyOrderCountLimit);
    }

    @ParameterizedTest
    @MethodSource("createOrderCountUnhappyCase")
    @DisplayName("20개를 넘어 주문 시, 주문할 수 없습니다.")
    void 주문개수_언해피케이스(User user) {
        // WHEN
        boolean verifyOrderMenu = EventJoinableChecker.verifyOrderCountLimit(user);

        // THEN
        assertFalse(verifyOrderMenu);
    }


}