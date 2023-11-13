package christmas.domain.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.domain.menu.Menu;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class OrderMapperTest {
    public static Stream<Arguments> generateValidMenuInput() {
        return Stream.of(
                Arguments.of(List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"))
        );
    }

    @ParameterizedTest
    @MethodSource("generateValidMenuInput")
    @DisplayName("사용자 입력으로부터 주문 메뉴와 주문 개수를 파싱합니다.")
    void 사용자입력_주문메뉴_주문개수_파싱(List<String> menuInput) {
        //  WHEN
        Order order = OrderMapper.createOrderByMenus(menuInput);

        //  THEN
        HashMap<Menu, Integer> orderExpected = new HashMap<>();
        orderExpected.put(Menu.T_BONE_STEAK, 1);
        orderExpected.put(Menu.BARBECUE_RIBS, 1);
        orderExpected.put(Menu.CHOCOLATE_CAKE, 2);
        orderExpected.put(Menu.ZERO_COKE, 1);
        assertEquals(orderExpected.keySet(), order.orderMenus().keySet());
        assertTrue(orderExpected.values().containsAll(order.orderMenus().values()));
    }
}