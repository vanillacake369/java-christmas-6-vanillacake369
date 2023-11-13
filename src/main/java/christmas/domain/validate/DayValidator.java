package christmas.domain.validate;

import static christmas.domain.validate.ExceptionFormat.formatException;

import java.util.function.Consumer;
import java.util.regex.Pattern;

public final class DayValidator {
    private static final String DAY_REGX = "^(?:[1-9]|[12][0-9]|3[01])$";
    public static Consumer<String> validateDay = dayInput -> validate(!Pattern.matches(DAY_REGX, dayInput),
            "유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    private DayValidator() {
    }

    private static void validate(boolean condition, String exceptionMessage) {
        if (condition) {
            throw new IllegalArgumentException(formatException(exceptionMessage));
        }
    }
}
