package christmas.domain.event.policy;

import christmas.domain.event.EventResultDTO;
import christmas.domain.event.batch.EventBatch;
import christmas.domain.event.policy.condition.ChristmasDiscountPolicyCondition;
import christmas.domain.user.User;

public class ChristmasDiscountPolicy implements EventPolicy {
    private final static Long DISCOUNT_START_PRICE = 1_000L;
    private final static Long DISCOUNT_PRICE_PER_DAY = 100L;
    private User user;

    public ChristmasDiscountPolicy(User user, EventBatch eventBatch) {
        this.user = user;
        if (ChristmasDiscountPolicyCondition.verifyCondition(user.getVisitDay())) {
            eventBatch.registerObserver(this);
        }
    }

    static Long getDiscountPrice(int visitDay) {
        return DISCOUNT_START_PRICE + DISCOUNT_PRICE_PER_DAY * (visitDay - 1);
    }

    @Override
    public void update(User user) {
        this.user = user;
    }

    @Override
    public void applyEvent(EventResultDTO resultDTO) {
        Long discountPrice = getDiscountPrice(user.getVisitDay());
        resultDTO.updateAppliedEventPrice(this, discountPrice);
    }
}
