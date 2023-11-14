package christmas.domain.event.policy;

import christmas.domain.user.User;

public interface Observer {
    void update(User user);
}
