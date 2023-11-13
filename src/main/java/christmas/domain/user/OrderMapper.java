package christmas.domain.user;

import christmas.domain.menu.Menu;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public final class OrderMapper {
    private OrderMapper() {
    }

    public static Order createOrderByMenus(List<String> orderInput) {
        HashMap<Menu, Integer> orderStatus = new HashMap<>();
        for (String order : orderInput) {
            List<String> orderState = Arrays.stream(order.split("-")).toList();
            Menu menu = Menu.findByMenuName(orderState.get(0));
            Integer orderCount = Integer.parseInt(orderState.get(1));
            orderStatus.put(menu, orderCount);
        }
        return new Order(orderStatus);
    }
}
