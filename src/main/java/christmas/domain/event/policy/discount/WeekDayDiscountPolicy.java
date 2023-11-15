package christmas.domain.event.policy.discount;

import static christmas.domain.event.policy.PolicyPrice.DAY_DISCOUNT_PRICE;

import christmas.domain.event.EventResultDTO;
import christmas.domain.event.batch.EventBatch;
import christmas.domain.event.policy.condition.WeekDayPolicyCondition;
import christmas.domain.user.Day;
import christmas.domain.user.User;

public class WeekDayDiscountPolicy implements DiscountPolicy {
    private final static String POLICY_NAME = "평일 할인";
    private User user;


    public WeekDayDiscountPolicy(User user, EventBatch eventBatch) {
        this.user = user;
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
        Long discountPrice = getDiscountPrice(user.visitDay());
        resultDTO.putAppliedEventPolicy(this, discountPrice);
    }

    @Override
    public String getPolicyName() {
        return POLICY_NAME;
    }

    @Override
    public Long getDiscountPrice(Day day) {
        long discountPrice = 0L;
        if (WeekDayPolicyCondition.verifyCondition(day.date())) {
            int dessertMenuCount = user.getDessertMenuCount();
            discountPrice = DAY_DISCOUNT_PRICE.value * dessertMenuCount;
        }
        return discountPrice;
    }
}
