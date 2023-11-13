package christmas.domain.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.utils.MenuParser;
import christmas.domain.validate.DayValidator;
import christmas.domain.validate.OrderValitor;
import java.util.List;
import java.util.function.Consumer;

public final class InputView {
    private InputView() {
    }

    public static int getDayInput() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n"
                + "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String dayInput = readUserInput(DayValidator.validateDay);
        Console.close();
        return Integer.parseInt(dayInput);
    }

    public static List<String> getOrderInput() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String orderInput = readUserInput(OrderValitor.validateOrders);
        Console.close();
        return MenuParser.parseByComma(orderInput);
    }

    static String readUserInput(Consumer<String> validation) {
        boolean sucess = false;
        String input = "";
        while (!sucess) {
            input = Console.readLine();
            sucess = isValidInput(validation, sucess, input);
        }
        return input;
    }

    static boolean isValidInput(Consumer<String> validation, boolean sucess, String input) {
        try {
            validation.accept(input);
            sucess = true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return sucess;
    }
}
