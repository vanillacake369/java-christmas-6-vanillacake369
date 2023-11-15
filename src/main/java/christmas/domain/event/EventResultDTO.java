package christmas.domain.event;

import christmas.domain.event.policy.discount.DiscountPolicy;
import christmas.domain.event.policy.event.EventPolicy;
import christmas.domain.menu.Menu;
import java.util.HashMap;
import java.util.Set;

public class EventResultDTO {
    //  증정 메뉴
    private final HashMap<Menu, Integer> giveawayMenus = new HashMap<>();
    //  혜택 내역
    private final HashMap<EventPolicy, Long> appliedEventPolicies = new HashMap<>();
    //  주문일
    private int day = 0;
    //  할인 전 총 주문 금액
    private Long eventPreviousPriceSum = 0L;
    //  주문메뉴
    private HashMap<Menu, Integer> orderMenus = null;
    //  할인 후 총 주문 금액
    private Long eventAfterPriceSum = 0L;
    // 총 혜택 금액
    private Long benefitPriceSum = 0L;
    //  총 할인 금액
    private Long discountPriceSum = 0L;
    //  12월 이벤트 배지
    private EventBadge eventBadge = null;

    public HashMap<Menu, Integer> getOrderMenus() {
        return orderMenus;
    }

    public int getDay() {
        return day;
    }

    public Long getDiscountPriceSum() {
        return discountPriceSum;
    }

    public HashMap<Menu, Integer> getGiveawayMenus() {
        return giveawayMenus;
    }

    public Long getEventPreviousPriceSum() {
        return eventPreviousPriceSum;
    }

    public HashMap<Menu, Integer> getOrder() {
        return orderMenus;
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

    public HashMap<EventPolicy, Long> getAppliedEventPolicies() {
        return appliedEventPolicies;
    }

    public void putGiveAwayMenu(Menu menu, Integer count) {
        this.giveawayMenus.put(menu, count);
    }

    public void putAppliedEventPolicy(EventPolicy policy, Long price) {
        appliedEventPolicies.put(policy, price);
    }

    public void updateEventResult(int day, HashMap<Menu, Integer> orderMenus, Long eventPreviousPriceSum) {
        // 주문일
        this.day = day;
        // 주문 메뉴 목록
        this.orderMenus = orderMenus;
        // 할인 전 총 주문 금액
        this.eventPreviousPriceSum = eventPreviousPriceSum;
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
        Set<EventPolicy> eventPolicies = appliedEventPolicies.keySet();
        for (EventPolicy policy : eventPolicies) {
            if (policy instanceof DiscountPolicy) {
                discountPriceSum += appliedEventPolicies.get(policy);
            }
        }
    }

    void updateBenefitPriceSum() {
        Set<EventPolicy> eventPolicies = appliedEventPolicies.keySet();
        for (EventPolicy policy : eventPolicies) {
            benefitPriceSum += appliedEventPolicies.get(policy);
        }
    }

    void updateEventAfterPriceSum() {
        this.eventAfterPriceSum = this.eventPreviousPriceSum - this.discountPriceSum;
    }

    void updateEventBadge() {
        this.eventBadge = EventBadge.getBadge(benefitPriceSum);
    }
}
