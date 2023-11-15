package christmas.domain.event.policy;

import christmas.domain.event.EventResultDTO;

public interface EventPolicy extends Observer {
    void applyEvent(EventResultDTO resultDTO);

    String getPolicyName();
}
