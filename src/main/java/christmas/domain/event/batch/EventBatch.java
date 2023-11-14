package christmas.domain.event.batch;

import christmas.domain.event.EventResultDTO;
import christmas.domain.event.policy.EventPolicy;
import christmas.domain.event.policy.Observer;
import christmas.domain.user.User;
import java.util.ArrayList;
import java.util.List;

public class EventBatch implements Subject {
    private final User user;

    private final List<EventPolicy> events;

    public EventBatch(User user) {
        this.user = user;
        this.events = new ArrayList<>();
        notifyObservers();
    }

    public void applyEventPolicies(EventResultDTO eventResultDTO) {
        for (EventPolicy event : events) {
            event.applyEvent(user, eventResultDTO);
        }
    }

    @Override
    public void registerObserver(Observer o) {
        events.add((EventPolicy) o);
    }

    @Override
    public void removeObserver(Observer o) {
        events.remove((EventPolicy) o);
    }

    @Override
    public void notifyObservers() {
        for (Observer event : events) {
            event.update(user);
        }
    }
}
