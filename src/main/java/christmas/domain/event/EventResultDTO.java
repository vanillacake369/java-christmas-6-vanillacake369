package christmas.domain.event;

import christmas.domain.event.policy.EventBadge;
import christmas.domain.event.policy.EventPolicy;
import christmas.domain.event.policy.discount.DiscountPolicy;
import christmas.domain.menu.Menu;
import christmas.domain.user.Order;
import christmas.domain.user.User;
import java.util.HashMap;
import java.util.Set;

public class EventResultDTO {
    //  증정 메뉴
    private final HashMap<Menu, Integer> giveawayMenus = new HashMap<>();
    //  혜택 내역
    private final HashMap<EventPolicy, Long> appliedDiscountPrices = new HashMap<>();
    //  할인 전 총 주문 금액
    private Long eventPreviousPriceSum = 0L;
    //  주문메뉴
    private Order order = null;
    //  할인 후 총 주문 금액
    private Long eventAfterPriceSum = 0L;
    // 총 혜택 금액
    private Long benefitPriceSum = 0L;
    //  총 할인 금액
    private Long discountPriceSum = 0L;
    //  12월 이벤트 배지
    private EventBadge eventBadge = null;

    public Long getDiscountPriceSum() {
        return discountPriceSum;
    }

    public HashMap<Menu, Integer> getGiveawayMenus() {
        return giveawayMenus;
    }

    public Long getEventPreviousPriceSum() {
        return eventPreviousPriceSum;
    }

    public Order getOrder() {
        return order;
    }

    public Long getEventAfterPriceSum() {
        return eventAfterPriceSum;
    }

    public Long getBenefitPriceSum() {
        return benefitPriceSum;
    }

    public EventBadge getEventBadge() {
        return eventBadge;
    }

    public HashMap<EventPolicy, Long> getAppliedDiscountPrices() {
        return appliedDiscountPrices;
    }

    public void putGiveAwayMenus(Menu menu, Integer count) {
        this.giveawayMenus.put(menu, count);
    }

    public void updateAppliedEventPrice(EventPolicy policy, Long price) {
        appliedDiscountPrices.put(policy, price);
    }

    public void updateEventResult(User user) {
        // 주문 메뉴 목록
        this.order = user.order();
        // 할인 전 총 주문 금액
        this.eventPreviousPriceSum = user.getPriceSum();
        // 할인 금액
        updateDiscountPriceSum();
        // 총 혜택 금액
        updateBenefitPriceSum();
        // 할인 후 총 주문 금액
        updateEventAfterPriceSum();
        // 12월 이벤트 배지
        updateEventBadge();
    }

    void updateDiscountPriceSum() {
        Set<EventPolicy> eventPolicies = appliedDiscountPrices.keySet();
        for (EventPolicy policy : eventPolicies) {
            if (policy instanceof DiscountPolicy) {
                discountPriceSum += appliedDiscountPrices.get(policy);
            }
        }
    }

    void updateBenefitPriceSum() {
        Set<EventPolicy> eventPolicies = appliedDiscountPrices.keySet();
        for (EventPolicy policy : eventPolicies) {
            benefitPriceSum += appliedDiscountPrices.get(policy);
        }
    }

    void updateEventAfterPriceSum() {
        this.eventAfterPriceSum = this.eventPreviousPriceSum - this.discountPriceSum;
    }

    void updateEventBadge() {
        this.eventBadge = EventBadge.getBadge(benefitPriceSum);
    }
}
