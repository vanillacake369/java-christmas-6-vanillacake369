package christmas.domain.event;

import christmas.domain.event.batch.EventBatch;
import christmas.domain.event.policy.discount.ChristmasDiscountPolicy;
import christmas.domain.event.policy.discount.SpecialDiscountPolicy;
import christmas.domain.event.policy.discount.WeekDayDiscountPolicy;
import christmas.domain.event.policy.discount.WeekEndDiscountPolicy;
import christmas.domain.event.policy.event.EventPolicy;
import christmas.domain.event.policy.event.GiftEventPolicy;
import christmas.domain.user.User;

public class EventService {
    private final EventBatch eventBatch;

    public EventService(User user) {
        this.eventBatch = new EventBatch(user);
        EventPolicy christmasDiscountPolicy = new ChristmasDiscountPolicy(user, eventBatch);
        EventPolicy weekDayDiscountPolicy = new WeekDayDiscountPolicy(user, eventBatch);
        EventPolicy weekEndDiscountPolicy = new WeekEndDiscountPolicy(user, eventBatch);
        EventPolicy specialDiscountPolicy = new SpecialDiscountPolicy(user, eventBatch);
        EventPolicy giftEvent = new GiftEventPolicy(user, eventBatch);
    }

    public void applyEvents(EventResultDTO resultDTO) {
        // 할인 적용
        eventBatch.applyEventPolicies(resultDTO);
    }
}
