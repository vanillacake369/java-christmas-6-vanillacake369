package christmas.domain.user;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuType;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public record User(Day visitDay, Order order) {
    public User {
        validateUser(visitDay, order);
    }

    public static void validateUser(Day visitDay, Order order) {
        Day.validateDay(visitDay.day(), visitDay.date());
        Order.validateOrder(order.orderMenus());
    }

    public int getDessertMenuCount() {
        AtomicInteger dessertMenuCount = new AtomicInteger();
        HashMap<Menu, Integer> orderMenus = order.orderMenus();
        Set<Menu> dessertMenus = orderMenus.keySet()
                .stream()
                .filter(menu -> menu.getMenuType().equals(MenuType.DESERT))
                .collect(Collectors.toSet());
        dessertMenus.forEach(menu -> dessertMenuCount.addAndGet(orderMenus.get(menu)));
        return dessertMenuCount.get();
    }

    public int getMainMenuCount() {
        AtomicInteger dessertMenuCount = new AtomicInteger();
        HashMap<Menu, Integer> orderMenus = order.orderMenus();
        Set<Menu> dessertMenus = orderMenus.keySet()
                .stream()
                .filter(menu -> menu.getMenuType().equals(MenuType.MAIN))
                .collect(Collectors.toSet());
        dessertMenus.forEach(menu -> dessertMenuCount.addAndGet(orderMenus.get(menu)));
        return dessertMenuCount.get();
    }

    public Long getPriceSum() {
        AtomicReference<Long> priceSum = new AtomicReference<>(0L);
        HashMap<Menu, Integer> orderMenus = order.orderMenus();
        orderMenus.keySet()
                .forEach(menu -> {
                    Integer orderCount = orderMenus.get(menu);
                    Long price = menu.getPrice();
                    priceSum.updateAndGet(v -> v + orderCount * price);
                });
        return priceSum.get();
    }

    public HashMap<Menu, Integer> getOrderMenus() {
        return this.order.orderMenus();
    }

    public int getVisitDay() {
        return visitDay.day();
    }

    public String getVisitDate() {
        return visitDay.date();
    }

    @Override
    public String toString() {
        return "User{" +
                "visitDay=" + visitDay +
                ", order=" + order +
                '}';
    }
}
