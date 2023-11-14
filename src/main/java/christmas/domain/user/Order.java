package christmas.domain.user;

import static christmas.domain.validate.CommonValidator.validateByCondition;
import static christmas.domain.validate.ValidateMessage.MENU_EXCEPTION_MESSAGE;

import christmas.domain.menu.Menu;
import christmas.domain.validate.OrderValitor;
import java.util.HashMap;

public record Order(HashMap<Menu, Integer> orderMenus) {
    public Order {
        validateOrder(orderMenus);
    }

    public static void validateOrder(HashMap<Menu, Integer> orderMenus) {
        boolean hasCountLessThenOne = orderMenus.values().stream()
                .anyMatch(OrderValitor::hasLessThenOne);
        validateByCondition(hasCountLessThenOne, MENU_EXCEPTION_MESSAGE.message);
    }
}
