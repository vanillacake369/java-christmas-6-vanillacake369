package christmas.domain.event;

import christmas.domain.user.User;

public class GiftEvent implements EventPolicy {
    public GiftEvent(User user, EventBatch eventBatch) {
    }
}
