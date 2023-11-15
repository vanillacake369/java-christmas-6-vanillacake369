package christmas.domain.event.policy;

import static christmas.domain.event.policy.PolicyPrice.DAY_DISCOUNT_PRICE;

import christmas.domain.event.EventResultDTO;
import christmas.domain.event.batch.EventBatch;
import christmas.domain.event.policy.condition.WeekDayPolicyCondition;
import christmas.domain.user.User;

public class WeekDayDiscountPolicy implements EventPolicy {
    private User user;

    public WeekDayDiscountPolicy(User user, EventBatch eventBatch) {
        if (WeekDayPolicyCondition.verifyCondition(user.getVisitDate())) {
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
        int dessertMenuCount = user.getDessertMenuCount();
        resultDTO.updateAppliedEventPrice(this, DAY_DISCOUNT_PRICE.value * dessertMenuCount);
    }

    @Override
    public String getPolicyName() {
        return "평일 할인";
    }
}
