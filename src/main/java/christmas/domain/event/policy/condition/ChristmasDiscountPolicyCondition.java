package christmas.domain.event.policy.condition;

public final class ChristmasDiscountPolicyCondition {
    private final static int CHRISTMAS_DISCOUNT_START_DAY = 1;
    private final static int CHRISTMAS_DISCOUNT_END_DAY = 25;

    private ChristmasDiscountPolicyCondition() {
    }

    public static boolean verifyCondition(int visitDay) {
        return CHRISTMAS_DISCOUNT_START_DAY <= visitDay && visitDay <= CHRISTMAS_DISCOUNT_END_DAY;
    }
}
