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
    }

    public void applyEventPolicies(EventResultDTO eventResultDTO) {
        // 모든 이벤트에 대해 update :: 이벤트 조건에 따라 구독실행/구독취소
        notifyObservers();
        // 구독한 이벤트 별로 이벤트 적용 
        for (EventPolicy event : events) {
            event.applyEvent(eventResultDTO);
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
