package christmas.domain.event.policy.event;

import christmas.domain.event.EventResultDTO;

public interface EventPolicy extends Observer {
    String getPolicyName();

    void applyEvent(EventResultDTO resultDTO);
}
