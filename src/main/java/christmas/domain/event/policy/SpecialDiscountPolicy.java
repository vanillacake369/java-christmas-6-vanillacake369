package christmas.domain.event.policy;

import christmas.domain.event.EventResultDTO;
import christmas.domain.event.batch.EventBatch;
import christmas.domain.user.User;

public class SpecialDiscountPolicy implements EventPolicy {
    public SpecialDiscountPolicy(User user, EventBatch eventBatch) {
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void applyEvent(User user, EventResultDTO resultDTO) {

    }
}
