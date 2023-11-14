package christmas.domain.validate;

import static christmas.domain.validate.CommonValidator.validateByCondition;
import static christmas.domain.validate.ValidateMessage.DAY_EXCEPTION_MESSAGE;
import static christmas.domain.validate.ValidateRegex.DAY_REGEX;

import christmas.domain.user.Dates;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public final class DayValidator {
    public static Consumer<String> validateDay = dayInput ->
            validateByCondition(!Pattern.matches(DAY_REGEX.regexPattern, dayInput), DAY_EXCEPTION_MESSAGE.message);
    public static Consumer<String> validateDate = dateInput -> {
        List<String> datesKor = Arrays.stream(Dates.values())
                .map(Dates::getDateKor)
                .toList();
        validateByCondition(!datesKor.contains(dateInput), DAY_EXCEPTION_MESSAGE.message);
    };

    private DayValidator() {
    }
}
