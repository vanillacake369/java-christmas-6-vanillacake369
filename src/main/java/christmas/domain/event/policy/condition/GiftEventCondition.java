package christmas.domain.event.policy.condition;

import static christmas.domain.event.policy.condition.PolicyConditionThreshold.GIFT_LOWER_BOUND_PRICE_SUM;

public final class GiftEventCondition {
    private GiftEventCondition() {
    }

    public static boolean verifyCondition(Long priceSum) {
        return priceSum >= GIFT_LOWER_BOUND_PRICE_SUM.value;
    }
}
