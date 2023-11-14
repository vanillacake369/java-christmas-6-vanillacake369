package christmas;

import christmas.domain.event.EventController;
import christmas.domain.user.User;
import christmas.domain.user.UserService;

public class Application {
    public static void main(String[] args) {
        UserService userService = new UserService();
        User user = userService.createUser();
        EventController eventController = new EventController(user);
        eventController.runEvent();
    }
}