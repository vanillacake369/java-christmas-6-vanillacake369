package christmas.domain.event.policy;

import static christmas.domain.event.policy.PolicyPrice.DAY_DISCOUNT_PRICE;

import christmas.domain.event.EventResultDTO;
import christmas.domain.event.batch.EventBatch;
import christmas.domain.event.policy.condition.WeekEndPolicyCondition;
import christmas.domain.user.User;

public class WeekEndDiscountPolicy implements EventPolicy {
    private User user;

    public WeekEndDiscountPolicy(User user, EventBatch eventBatch) {
        if (WeekEndPolicyCondition.verifyCondition(user.getVisitDate())) {
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
        int mainMenuCount = user.getMainMenuCount();
        resultDTO.updateAppliedEventPrice(this, DAY_DISCOUNT_PRICE.value * mainMenuCount);
    }

    @Override
    public String getPolicyName() {
        return "주말 할인";
    }
}
