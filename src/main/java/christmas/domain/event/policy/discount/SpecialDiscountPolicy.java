package christmas.domain.event.policy.discount;

import static christmas.domain.event.policy.PolicyPrice.SPECIAL_DISCOUNT_PRICE;

import christmas.domain.event.EventResultDTO;
import christmas.domain.event.batch.EventBatch;
import christmas.domain.event.policy.condition.SpecialDiscountPolicyCondition;
import christmas.domain.user.Day;
import christmas.domain.user.User;

public class SpecialDiscountPolicy implements DiscountPolicy {
    private final static String POLICY_NAME = "특별 할인";
    private User user;

    public SpecialDiscountPolicy(User user, EventBatch eventBatch) {
        this.user = user;
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
        Long discountPrice = getDiscountPrice(user.visitDay());
        resultDTO.putAppliedEventPolicy(this, discountPrice);
    }

    @Override
    public String getPolicyName() {
        return POLICY_NAME;
    }

    @Override
    public Long getDiscountPrice(Day day) {
        Long discountPrice = 0L;
        if (SpecialDiscountPolicyCondition.verifyCondition(day.day())) {
            discountPrice = SPECIAL_DISCOUNT_PRICE.value;
        }
        return discountPrice;
    }
}
