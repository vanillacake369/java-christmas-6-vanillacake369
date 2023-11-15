package christmas;

import christmas.domain.event.EventController;
import christmas.domain.user.User;
import christmas.domain.user.UserService;

public class Application {
    public static void main(String[] args) {
        UserService userService = new UserService();
        User user = userService.createUser();
        System.out.println(user.toString());
        EventController eventController = new EventController(user);
        eventController.runEvent();
    }
}