package christmas.domain.event.policy.event;

import static christmas.domain.menu.Menu.CHAMPAGNE;

import christmas.domain.event.EventResultDTO;
import christmas.domain.event.batch.EventBatch;
import christmas.domain.event.policy.condition.GiftEventCondition;
import christmas.domain.menu.Menu;
import christmas.domain.user.User;

public class GiftEventPolicy implements EventPolicy {
    private final static Menu GIFT = CHAMPAGNE;
    private final static String POLICY_NAME = "증정 이벤트";

    private final static Integer GIFT_COUNT = 1;
    private User user;

    public GiftEventPolicy(User user, EventBatch eventBatch) {
        this.user = user;
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
        resultDTO.putAppliedEventPolicy(this, GIFT.getPrice());
        resultDTO.putGiveAwayMenu(GIFT, GIFT_COUNT);
    }

    @Override
    public String getPolicyName() {
        return POLICY_NAME;
    }
}
