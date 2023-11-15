package christmas.domain.event.policy.discount;

import static christmas.domain.event.policy.PolicyPrice.CHRISTMAS_DISCOUNT_PRICE_PER_DAY;
import static christmas.domain.event.policy.PolicyPrice.CHRISTMAS_DISCOUNT_START_PRICE;

import christmas.domain.event.EventResultDTO;
import christmas.domain.event.batch.EventBatch;
import christmas.domain.event.policy.condition.ChristmasDiscountPolicyCondition;
import christmas.domain.user.Day;
import christmas.domain.user.User;

public class ChristmasDiscountPolicy implements DiscountPolicy {
    private final static String POLICY_NAME = "크리스마스 디데이 할인";
    private User user;

    public ChristmasDiscountPolicy(User user, EventBatch eventBatch) {
        this.user = user;
        if (ChristmasDiscountPolicyCondition.verifyCondition(user.getVisitDay())) {
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
        return CHRISTMAS_DISCOUNT_START_PRICE.value
                + CHRISTMAS_DISCOUNT_PRICE_PER_DAY.value
                * (day.day() - 1);
    }
}
