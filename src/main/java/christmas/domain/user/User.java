package christmas.domain.user;

import christmas.domain.menu.Menu;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public record User(Day visitDay, Order order) {
    public User {
        validateUser(visitDay, order);
    }

    public static void validateUser(Day visitDay, Order order) {
        Day.validateDay(visitDay.day(), visitDay.date());
        Order.validateOrder(order.orderMenus());
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
}
