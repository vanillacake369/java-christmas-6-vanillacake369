package christmas.domain.user;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.domain.menu.Menu;
import java.util.HashMap;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class OrderTest {
    public static Stream<Arguments> createValidOrder() {
        HashMap<Menu, Integer> orderMenus = new HashMap<>();
        orderMenus.put(Menu.ZERO_COKE, 1);
        orderMenus.put(Menu.BARBECUE_RIBS, 1);
        return Stream.of(
                Arguments.of(orderMenus)
        );
    }

    public static Stream<Arguments> createInvalidOrder() {
        HashMap<Menu, Integer> orderMenus = new HashMap<>();
        orderMenus.put(Menu.ZERO_COKE, -1);
        return Stream.of(
                Arguments.of(orderMenus)
        );
    }

    @ParameterizedTest
    @MethodSource("createValidOrder")
    @DisplayName("유효한 개수의 주문 시, 정상처리합니다.")
    void 메뉴개수_해피케이스(HashMap<Menu, Integer> orderMenus) {
        // WHEN
        // THEN
        assertDoesNotThrow(() -> new Order(orderMenus));
    }

    @ParameterizedTest
    @MethodSource("createInvalidOrder")
    @DisplayName("무효한 개수의 주문 시, 예외처리합니다.")
    void 메뉴개수_언해피케이스(HashMap<Menu, Integer> orderMenus) {
        // WHEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Order(orderMenus));
        // THEN
        assertTrue(exception.getMessage().startsWith("[ERROR]"));
    }
}