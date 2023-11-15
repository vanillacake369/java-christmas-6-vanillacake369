package christmas.domain.event.batch;

import christmas.domain.event.policy.event.Observer;

public interface Subject {
    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers();
}
