package christmas.domain.event.policy.condition;

import static christmas.domain.event.policy.condition.PolicyConditionThreshold.CHRISTMAS_DISCOUNT_END_DAY;
import static christmas.domain.event.policy.condition.PolicyConditionThreshold.CHRISTMAS_DISCOUNT_START_DAY;

public final class ChristmasDiscountPolicyCondition {
    private ChristmasDiscountPolicyCondition() {
    }

    public static boolean verifyCondition(int visitDay) {
        return CHRISTMAS_DISCOUNT_START_DAY.value <= visitDay
                && visitDay <= CHRISTMAS_DISCOUNT_END_DAY.value;
    }
}
