package christmas.domain.event;

import christmas.domain.user.User;

public class ChristmasDiscountPolicy implements DiscountPolicy {
    private final User user;

    public ChristmasDiscountPolicy(User user, EventBatch eventBatch) {
        this.user = user;
    }
}
