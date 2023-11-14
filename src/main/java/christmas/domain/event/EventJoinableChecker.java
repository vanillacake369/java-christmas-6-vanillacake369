package christmas.domain.event;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuType;
import christmas.domain.user.User;
import java.util.Set;
import java.util.stream.Collectors;

public class EventJoinableChecker {
    private final static long PRICE_LOWER_BOUND = 10_000L;
    private final static int ORDER_COUNT_LIMIT = 20;

    public static boolean isJoinable(User user) {
        return verifyLowerBound(user) & verifyOrderMenu(user) & verifyOrderCountLimit(user);
    }

    static boolean verifyLowerBound(User user) {
        Long priceSum = user.getPriceSum();
        return priceSum >= PRICE_LOWER_BOUND;
    }

    static boolean verifyOrderMenu(User user) {
        Set<MenuType> orderMenuTypes = user.getOrderMenus().keySet().stream()
                .map(Menu::getMenuType)
                .collect(Collectors.toSet());
        return orderMenuTypes.size() != 1 || !orderMenuTypes.contains(MenuType.BEVERAGE);
    }

    static boolean verifyOrderCountLimit(User user) {
        Integer orderCountSum = user.getOrderMenus().values().stream()
                .reduce(0, Integer::sum);
        System.out.println(orderCountSum);
        return orderCountSum <= ORDER_COUNT_LIMIT;
    }
}
