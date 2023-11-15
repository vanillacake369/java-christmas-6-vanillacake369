package christmas.domain.event.policy;

import static christmas.domain.menu.Menu.CHAMPAGNE;

import christmas.domain.event.EventResultDTO;
import christmas.domain.event.batch.EventBatch;
import christmas.domain.event.policy.condition.GiftEventCondition;
import christmas.domain.menu.Menu;
import christmas.domain.user.User;

public class GiftEventPolicy implements EventPolicy {
    private final static Menu GIFT = CHAMPAGNE;
    private User user;

    public GiftEventPolicy(User user, EventBatch eventBatch) {
        if (GiftEventCondition.verifyCondition(user.getPriceSum())) {
            eventBatch.registerObserver(this);
            return;
        }
        eventBatch.removeObserver(this);
    }

    @Override
    public void update(User user) {
        this.user = user;
    }

    @Override
    public void applyEvent(EventResultDTO resultDTO) {
        resultDTO.putGiveAwayMenus(GIFT);
    }
}
