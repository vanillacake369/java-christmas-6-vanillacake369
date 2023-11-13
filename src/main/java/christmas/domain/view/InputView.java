package christmas.domain.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.validate.DayValidator;
import java.util.function.Consumer;

public final class InputView {
    private InputView() {
    }

    public static int getDay() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n"
                + "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        boolean sucess = false;
        String input = "";
        while (!sucess) {
            input = Console.readLine();
            sucess = isValidInput(DayValidator.validateDay, sucess, input);
        }
        Console.close();
        return Integer.parseInt(input);
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
