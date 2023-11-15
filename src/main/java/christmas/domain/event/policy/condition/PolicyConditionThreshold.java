package christmas.domain.event.policy.condition;

public enum PolicyConditionThreshold {
    CHRISTMAS_DISCOUNT_START_DAY(1L),
    CHRISTMAS_DISCOUNT_END_DAY(25L),
    GIFT_LOWER_BOUND_PRICE_SUM(120_000L),
    WEEKDAY_START(0L),
    WEEKDAY_END(4L),
    WEEKEND_START(5L);

    public final Long value;

    PolicyConditionThreshold(Long value) {
        this.value = value;
    }
}
