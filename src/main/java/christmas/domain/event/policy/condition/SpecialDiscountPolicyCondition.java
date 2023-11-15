package christmas.domain.event.policy.condition;

import christmas.domain.calendar.StarredDates;

public final class SpecialDiscountPolicyCondition {
    private SpecialDiscountPolicyCondition() {
    }

    public static boolean verifyCondition(int visitDay) {
        return StarredDates.isStarredDate(visitDay);
    }
}
