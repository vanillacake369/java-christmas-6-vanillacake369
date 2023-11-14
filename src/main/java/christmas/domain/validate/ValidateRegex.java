package christmas.domain.validate;

public enum ValidateRegex {
    DAY_REGEX("^(?:[1-9]|[12][0-9]|3[01])$"),
    MENU_INPUT_FORM_REGEX("^[가-힣]+-\\d+$");
    final String regexPattern;

    ValidateRegex(String regexPattern) {
        this.regexPattern = regexPattern;
    }
}
