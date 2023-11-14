package christmas.domain.validate;

public enum ValidateMessage {
    MENU_EXCEPTION_MESSAGE("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    DAY_EXCEPTION_MESSAGE("유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    public final String message;

    ValidateMessage(String message) {
        this.message = message;
    }
}
