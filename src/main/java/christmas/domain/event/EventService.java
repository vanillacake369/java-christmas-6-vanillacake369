package christmas.domain.event;

import christmas.domain.event.batch.EventBatch;
import christmas.domain.event.policy.EventPolicy;
import christmas.domain.event.policy.GiftEventPolicy;
import christmas.domain.event.policy.discount.ChristmasDiscountPolicy;
import christmas.domain.event.policy.discount.SpecialDiscountPolicy;
import christmas.domain.event.policy.discount.WeekDayDiscountPolicy;
import christmas.domain.event.policy.discount.WeekEndDiscountPolicy;
import christmas.domain.user.User;

public class EventService {
    private final User user;
    private final EventBatch eventBatch;
    private final EventPolicy christmasDiscountPolicy;
    private final EventPolicy weekDayDiscountPolicy;
    private final EventPolicy weekEndDiscountPolicy;
    private final EventPolicy specialDiscountPolicy;
    private final EventPolicy giftEvent;

    public EventService(User user) {
        this.user = user;
        this.eventBatch = new EventBatch(user);
        this.christmasDiscountPolicy = new ChristmasDiscountPolicy(user, eventBatch);
        this.weekDayDiscountPolicy = new WeekDayDiscountPolicy(user, eventBatch);
        this.weekEndDiscountPolicy = new WeekEndDiscountPolicy(user, eventBatch);
        this.specialDiscountPolicy = new SpecialDiscountPolicy(user, eventBatch);
        this.giftEvent = new GiftEventPolicy(user, eventBatch);
    }

    public void applyEvents(EventResultDTO resultDTO) {
        // 할인 적용
        eventBatch.applyEventPolicies(resultDTO);
    }
}
