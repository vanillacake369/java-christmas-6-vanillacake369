package christmas.domain.validate;

import static christmas.domain.calendar.EventTime.MONTH;
import static christmas.domain.calendar.EventTime.YEAR;
import static christmas.domain.validate.CommonValidator.validateByCondition;
import static christmas.domain.validate.ValidateMessage.DAY_EXCEPTION_MESSAGE;
import static christmas.domain.validate.ValidateRegex.DAY_REGEX;

import christmas.domain.calendar.Dates;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public final class DayValidator {
    public static Consumer<String> validateDay = dayInput ->
            validateByCondition(!Pattern.matches(DAY_REGEX.regexPattern, dayInput), DAY_EXCEPTION_MESSAGE.message);
    public static BiConsumer<Integer, String> validateDate = (dayInput, dateInput) -> {
        LocalDate localDate = LocalDate.of(YEAR.time, MONTH.time, dayInput);
        TextStyle textStyle = TextStyle.FULL;
        String dateKor = localDate.getDayOfWeek().getDisplayName(textStyle, Locale.KOREAN);
        validateByCondition(!dateKor.equals(dateInput), DAY_EXCEPTION_MESSAGE.message);

        List<String> datesKor = Arrays.stream(Dates.values())
                .map(Dates::getDateKor)
                .toList();
        validateByCondition(!datesKor.contains(dateInput), DAY_EXCEPTION_MESSAGE.message);
    };

    private DayValidator() {
    }
}
