package christmas.domain.event.policy.discount;

import christmas.domain.event.policy.event.EventPolicy;
import christmas.domain.event.policy.event.Observer;
import christmas.domain.user.Day;

public interface DiscountPolicy extends EventPolicy, Observer {
    Long getDiscountPrice(Day day);
}
