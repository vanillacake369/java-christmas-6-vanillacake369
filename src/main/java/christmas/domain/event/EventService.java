package christmas.domain.event;

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
        this.giftEvent = new GiftEvent(user, eventBatch);
    }

    public EventResult applyEvents(User user) {
        // event batch :: updates list of events -> event service
        // event service apply event
        // create requestDto
        // print requestDto
        return new EventResult();
    }
}
