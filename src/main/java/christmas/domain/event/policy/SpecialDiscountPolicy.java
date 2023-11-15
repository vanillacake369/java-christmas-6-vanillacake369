package christmas.domain.event.policy;

import static christmas.domain.event.policy.PolicyPrice.SPECIAL_DISCOUNT_PRICE;

import christmas.domain.event.EventResultDTO;
import christmas.domain.event.batch.EventBatch;
import christmas.domain.event.policy.condition.SpecialDiscountPolicyCondition;
import christmas.domain.user.User;

public class SpecialDiscountPolicy implements EventPolicy {
    private User user;

    public SpecialDiscountPolicy(User user, EventBatch eventBatch) {
        if (SpecialDiscountPolicyCondition.verifyCondition(user.getVisitDay())) {
            eventBatch.registerObserver(this);
            return;
        }
        eventBatch.removeObserver(this);
    }

    @Override
    public void update(User user) {
        this.user = user;
    }

    @Override
    public void applyEvent(EventResultDTO resultDTO) {
        resultDTO.updateAppliedEventPrice(this, SPECIAL_DISCOUNT_PRICE.value);
    }

    @Override
    public String getPolicyName() {
        return "특별 할인";
    }
}
