package christmas.domain.event.policy.event;

import christmas.domain.user.User;

public interface Observer {
    void update(User user);
}
