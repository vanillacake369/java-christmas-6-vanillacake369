package christmas.domain.validate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MenuValidatorTest {

    /*  메뉴 형식 검증   */
    public static Stream<Arguments> generateValidMenuInput() {
        return Stream.of(
                Arguments.of(List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"))
        );
    }

    public static Stream<Arguments> generateInvalidForm() {
        return Stream.of(
                Arguments.of(List.of("티본스테이크1")),
                Arguments.of(List.of("티본스테이크^1")),
                Arguments.of(List.of("티본스테이크")),
                Arguments.of(List.of("1")),
                Arguments.of(List.of("1-티본스테이크"))
        );
    }

    public static Stream<Arguments> generateUnknownMenu() {
        return Stream.of(
                Arguments.of(List.of("푸팟퐁커리-1")),
                Arguments.of(List.of("삼각김밥-2")),
                Arguments.of(List.of("이런기회가-3")),
                Arguments.of(List.of("언제또온단말인가-4")),
                Arguments.of(List.of("많이배워서좋았다-5"))
        );
    }

    public static Stream<Arguments> generateDuplicatedMenuInput() {
        return Stream.of(
                Arguments.of(List.of("티본스테이크-1", "티본스테이크-1", "티본스테이크-1"))
        );
    }

    public static Stream<Arguments> generateInvalidOrderCount() {
        return Stream.of(
                Arguments.of(List.of("티본스테이크-0"))
        );
    }

    @ParameterizedTest
    @MethodSource("generateValidMenuInput")
    @DisplayName("올바른 메뉴형식인 경우, 정상처리합니다.")
    void 메뉴형식_검증_해피케이스(List<String> parsedMenuInput) {
        assertDoesNotThrow(() -> MenuValidator.validateValidInputForm(parsedMenuInput));
    }

    /*  메뉴판에 없는 메뉴 입력 검증  */

    @ParameterizedTest
    @MethodSource("generateInvalidForm")
    @DisplayName("잘못된 메뉴형식인 경우, 예외처리합니다.")
    void 메뉴형식_검증_언해피케이스(List<String> parsedMenuInput) {
        //  WHEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> MenuValidator.validateValidInputForm(parsedMenuInput));
        //  THEN
        assertTrue(exception.getMessage().startsWith("[ERROR]"));
        assertTrue(exception.getMessage().contains(ValidateMessage.MENU_EXCEPTION_MESSAGE.message));
    }

    @ParameterizedTest
    @MethodSource("generateValidMenuInput")
    @DisplayName("메뉴판에 있는 메뉴 입력인 경우, 정상처리합니다.")
    void 메뉴판에_있는_메뉴입력_해피케이스(List<String> parsedMenuInput) {
        assertDoesNotThrow(() -> MenuValidator.validateValidMenu(parsedMenuInput));
    }

    @ParameterizedTest
    @MethodSource("generateUnknownMenu")
    @DisplayName("메뉴판에 없는 메뉴 입력인 경우, 정상처리합니다.")
    void 메뉴판에_있는_메뉴입력_언해피케이스(List<String> parsedMenuInput) {
        //  WHEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> MenuValidator.validateValidMenu(parsedMenuInput));
        //  THEN
        assertTrue(exception.getMessage().startsWith("[ERROR]"));
        assertTrue(exception.getMessage().contains(ValidateMessage.MENU_EXCEPTION_MESSAGE.message));
    }

    /*  중복 메뉴 입력 검증   */
    @ParameterizedTest
    @MethodSource("generateValidMenuInput")
    @DisplayName("중복되지 않는 메뉴 입력인 경우, 정상처리합니다.")
    void 중복메뉴입력_해피케이스(List<String> parsedMenuInput) {
        assertDoesNotThrow(() -> MenuValidator.validateDistinctOrderMenu(parsedMenuInput));
    }

    @ParameterizedTest
    @MethodSource("generateDuplicatedMenuInput")
    @DisplayName("중복 메뉴 입력인 경우, 예외처리합니다.")
    void 중복메뉴입력_언해피케이스(List<String> parsedMenuInput) {
        //  WHEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> MenuValidator.validateDistinctOrderMenu(parsedMenuInput));
        //  THEN
        assertTrue(exception.getMessage().startsWith("[ERROR]"));
        assertTrue(exception.getMessage().contains(ValidateMessage.MENU_EXCEPTION_MESSAGE.message));
    }

    /*  주문 메뉴 개수 1 이상의 숫자 검증  */
    @ParameterizedTest
    @MethodSource("generateValidMenuInput")
    @DisplayName("주문 메뉴 개수가 1 이상의 숫자인 경우, 정상처리합니다.")
    void 주문메뉴개수_해피케이스(List<String> parsedMenuInput) {
        assertDoesNotThrow(() -> MenuValidator.validateValidOrderCount(parsedMenuInput));
    }

    @ParameterizedTest
    @MethodSource("generateInvalidOrderCount")
    @DisplayName("주문 메뉴 개수가 1 미만 숫자인 경우, 예외처리합니다.")
    void 주문메뉴개수_언해피케이스(List<String> parsedMenuInput) {
        //  WHEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> MenuValidator.validateValidOrderCount(parsedMenuInput));
        //  THEN
        assertTrue(exception.getMessage().startsWith("[ERROR]"));
        assertTrue(exception.getMessage().contains(ValidateMessage.MENU_EXCEPTION_MESSAGE.message));
    }
}