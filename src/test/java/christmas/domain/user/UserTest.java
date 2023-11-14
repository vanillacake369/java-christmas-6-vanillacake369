package christmas.domain.user;

import static christmas.domain.menu.Menu.SEAFOOD_PASTA;
import static christmas.domain.menu.Menu.TAPAS;
import static christmas.domain.menu.Menu.T_BONE_STEAK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.menu.Menu;
import java.util.HashMap;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class UserTest {
    private static Stream<Arguments> createValidUser() {
        Day day = new Day(3, "금요일");
        HashMap<Menu, Integer> orderMenus = new HashMap<>();
        orderMenus.put(T_BONE_STEAK, 1);
        orderMenus.put(TAPAS, 1);
        orderMenus.put(SEAFOOD_PASTA, 1);
        Order order = new Order(orderMenus);
        User user = new User(day, order);
        return Stream.of(
                Arguments.of(user)
        );
    }

    @ParameterizedTest
    @MethodSource("createValidUser")
    @DisplayName("사용자 주문의 총 가격합을 구합니다.")
    void 사용자주문_총가격_계산(User user) {
        // WHEN
        Long priceSum = user.getPriceSum();
        Long expectedPriceSum = 55_000L + 8_000L + 35_000L;

        // THEN
        assertEquals(priceSum, expectedPriceSum);
    }

    @Test
    @DisplayName("불변객체에 대한 -- 동일한 값에 대해 -- 동치성을 지원합니다.")
    void 동치성_테스트() {
        // GIVEN
        Day day = new Day(3, "금요일");
        HashMap<Menu, Integer> orderMenus = new HashMap<>();
        orderMenus.put(T_BONE_STEAK, 1);
        orderMenus.put(TAPAS, 1);
        orderMenus.put(SEAFOOD_PASTA, 1);
        Order order = new Order(orderMenus);
        // WHEN
        User user1 = new User(day, order);
        User user2 = new User(day, order);
        // THEN
        assertEquals(user1, user2);
    }
}