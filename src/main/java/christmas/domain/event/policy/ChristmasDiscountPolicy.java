package christmas.domain.event.policy;

import static christmas.domain.event.policy.PolicyPrice.CHRISTMAS_DISCOUNT_PRICE_PER_DAY;
import static christmas.domain.event.policy.PolicyPrice.CHRISTMAS_DISCOUNT_START_PRICE;

import christmas.domain.event.EventResultDTO;
import christmas.domain.event.batch.EventBatch;
import christmas.domain.event.policy.condition.ChristmasDiscountPolicyCondition;
import christmas.domain.user.User;

public class ChristmasDiscountPolicy implements EventPolicy {
    private User user;

    public ChristmasDiscountPolicy(User user, EventBatch eventBatch) {
        if (ChristmasDiscountPolicyCondition.verifyCondition(user.getVisitDay())) {
            eventBatch.registerObserver(this);
            return;
        }
        eventBatch.removeObserver(this);
    }

    static Long getDiscountPrice(int visitDay) {
        return CHRISTMAS_DISCOUNT_START_PRICE.value + CHRISTMAS_DISCOUNT_PRICE_PER_DAY.value * (visitDay - 1);
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
