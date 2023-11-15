package christmas.domain.event.policy.discount;

import static christmas.domain.event.policy.PolicyPrice.DAY_DISCOUNT_PRICE;

import christmas.domain.event.EventResultDTO;
import christmas.domain.event.batch.EventBatch;
import christmas.domain.event.policy.condition.WeekEndPolicyCondition;
import christmas.domain.user.Day;
import christmas.domain.user.User;

public class WeekEndDiscountPolicy implements DiscountPolicy {
    private User user;

    public WeekEndDiscountPolicy(User user, EventBatch eventBatch) {
        this.user = user;
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
        Long discountPrice = getDiscountPrice(user.visitDay());
        resultDTO.updateAppliedEventPrice(this, discountPrice);
    }

    @Override
    public String getPolicyName() {
        return "주말 할인";
    }

    @Override
    public Long getDiscountPrice(Day day) {
        long discountPrice = 0L;
        if (WeekEndPolicyCondition.verifyCondition(day.date())) {
            int mainMenuCount = user.getMainMenuCount();
            discountPrice = DAY_DISCOUNT_PRICE.value * mainMenuCount;
        }
        return discountPrice;
    }
}
