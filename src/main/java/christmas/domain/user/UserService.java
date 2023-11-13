package christmas.domain.user;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.view.InputView;
import java.util.List;

public class UserService {
    public User createUser() {
        int dayInput = InputView.getDayInput();
        Day day = DayMapper.createDayByDayInput(dayInput);

        List<String> orderInput = InputView.getOrderInput();
        Order order = OrderMapper.createOrderByMenus(orderInput);

        Console.close();
        return new User(day, order);
    }
}
