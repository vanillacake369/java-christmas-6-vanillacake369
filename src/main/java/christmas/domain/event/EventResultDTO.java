package christmas.domain.event;

import static christmas.domain.menu.Menu.CHAMPAGNE;

import christmas.domain.event.policy.EventPolicy;
import christmas.domain.menu.Menu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventResultDTO {
    //  할인 전 총 주문 금액
    private final Long eventPreviousPrice;
    //  증정 메뉴
    private final List<Menu> giveawayMenus = new ArrayList<>();
    //  혜택 내역
    private final HashMap<EventPolicy, Long> appliedEventPrice = new HashMap<>();
    //  총 혜택 금액
    private Integer eventPriceSum;
    //  할인 후 예상 결제 금액
    private Long eventAfterPrice;
    //  12월 이벤트 배지
    private EventBadge eventBadge;

    private EventResultDTO(Long eventPreviousPrice) {
        this.eventPreviousPrice = eventPreviousPrice;
    }

    static EventResultDTO initEventResultDTO(Long eventPreviousPrice) {
        return new EventResultDTO(eventPreviousPrice);
    }

    public void putGiveAwayMenus() {
        this.giveawayMenus.add(CHAMPAGNE);
    }

    public void updateAppliedEventPrice(EventPolicy policy, Long price) {
        appliedEventPrice.put(policy, price);
    }

    public Long getEventPreviousPrice() {
        return eventPreviousPrice;
    }

    public List<Menu> getGiveawayMenus() {
        return giveawayMenus;
    }

    public HashMap<EventPolicy, Long> getAppliedEventPrice() {
        return appliedEventPrice;
    }

    public Integer getEventPriceSum() {
        return eventPriceSum;
    }

    public Long getEventAfterPrice() {
        return eventAfterPrice;
    }

    public EventBadge getEventBadge() {
        return eventBadge;
    }
}
