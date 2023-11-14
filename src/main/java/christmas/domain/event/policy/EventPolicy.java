package christmas.domain.event.policy;

import christmas.domain.event.EventResultDTO;
import christmas.domain.user.User;

public interface EventPolicy extends Observer {
    void applyEvent(User user, EventResultDTO resultDTO);
}
