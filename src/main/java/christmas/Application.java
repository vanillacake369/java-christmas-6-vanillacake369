package christmas;

import christmas.domain.event.EventController;
import christmas.domain.event.EventService;
import christmas.domain.user.UserService;

public class Application {
    public static void main(String[] args) {
        EventController eventController = new EventController(new UserService(), new EventService());
        eventController.runEvent();
    }
}