package christmas.domain.event;

import christmas.domain.user.User;
import christmas.domain.user.UserService;

public class EventController {
    private final UserService userService;
    private final EventService eventService;

    public EventController(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    public void runEvent() {
        User user = userService.createUser();
        boolean isUserJoinable = EventJoinableChecker.isJoinable(user);
        if (isUserJoinable) {
            // user -> event service(subject)
            // event service -> event batch
            // event batch :: updates list of events -> event service
            // event service apply event
            // create requestDto
            // print requestDto
        }
    }
}
