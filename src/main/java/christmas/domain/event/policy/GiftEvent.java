package christmas.domain.event.policy;

import christmas.domain.event.EventResultDTO;
import christmas.domain.event.batch.EventBatch;
import christmas.domain.user.User;

public class GiftEvent implements EventPolicy {
    public GiftEvent(User user, EventBatch eventBatch) {
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void applyEvent(EventResultDTO resultDTO) {

    }
}
