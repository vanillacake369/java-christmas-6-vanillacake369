package christmas.domain.event.batch;

import christmas.domain.event.policy.Observer;

public interface Subject {
    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers();
}
