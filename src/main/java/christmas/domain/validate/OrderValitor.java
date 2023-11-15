package christmas.domain.validate;

import static christmas.domain.validate.CommonValidator.validateByCondition;
import static christmas.domain.validate.ValidateMessage.MENU_EXCEPTION_MESSAGE;
import static christmas.domain.validate.ValidateRegex.MENU_INPUT_FORM_REGEX;

import christmas.domain.menu.Menu;
import christmas.domain.utils.MenuParser;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public final class OrderValitor {
    public static Consumer<String> validateOrders = menuInput -> {
        List<String> parsedMenuInput = MenuParser.parseByComma(menuInput);

        validateValidInputForm(parsedMenuInput);    // 메뉴 형식 검증
        validateValidMenu(parsedMenuInput);         // 메뉴판에 없는 메뉴 입력 검증
        validateDistinctOrderMenu(parsedMenuInput); // 중복 메뉴 입력 검증
        validateValidOrderCount(parsedMenuInput);   // 주문 메뉴 개수 1 이상의 숫자 검증
    };

    private OrderValitor() {
    }

    static void validateValidInputForm(List<String> parsedMenuInput) throws IllegalArgumentException {
        boolean formValidationResult = parsedMenuInput.stream()
                .anyMatch(input -> !Pattern.matches(MENU_INPUT_FORM_REGEX.regexPattern, input));
        validateByCondition(formValidationResult, MENU_EXCEPTION_MESSAGE.message);
    }

    static void validateValidMenu(List<String> parsedMenuInput) throws IllegalArgumentException {
        List<String> menus = Menu.getMenuNames();
        boolean menuValidationResult = parsedMenuInput.stream()
                .map(input -> input.split("-")[0])
                .anyMatch(orderMenu -> !menus.contains(orderMenu));

        validateByCondition(menuValidationResult, MENU_EXCEPTION_MESSAGE.message);
    }

    static void
    validateDistinctOrderMenu(List<String> parsedMenuInput) throws IllegalArgumentException {
        List<String> distinctMenuInput = parsedMenuInput.stream()
                .map(input -> input.split("-")[0])
                .distinct()
                .toList();
        boolean distinctMenuValidationResult = !Objects.equals(distinctMenuInput.size(), parsedMenuInput.size());
        validateByCondition(distinctMenuValidationResult, MENU_EXCEPTION_MESSAGE.message);
    }

    static void validateValidOrderCount(List<String> parsedMenuInput) throws IllegalArgumentException {
        boolean orderCountValidationResult = parsedMenuInput.stream()
                .map(input -> input.split("-")[1])
                .map(Integer::parseInt)
                .anyMatch(OrderValitor::hasLessThenOne);
        validateByCondition(orderCountValidationResult, MENU_EXCEPTION_MESSAGE.message);
    }

    public static boolean hasLessThenOne(Integer orderCount) {
        return orderCount < 1;
    }
}

