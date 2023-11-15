package christmas.domain.event.policy;

public enum PolicyPrice {
    CHRISTMAS_DISCOUNT_START_PRICE(1_000L),
    CHRISTMAS_DISCOUNT_PRICE_PER_DAY(100L),
    SPECIAL_DISCOUNT_PRICE(1_000L),
    DAY_DISCOUNT_PRICE(2_023L),
    ;

    public final Long value;

    PolicyPrice(Long value) {
        this.value = value;
    }
}
