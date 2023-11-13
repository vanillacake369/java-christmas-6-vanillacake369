package christmas.domain.validate;

public final class CommonValidator {
    private CommonValidator() {
    }

    public static String formatException(String exceptionMessage) {
        return String.format("[ERROR] %s", exceptionMessage);
    }

    public static void validateByCondition(boolean condition, String exceptionMessage) throws IllegalArgumentException {
        if (condition) {
            throw new IllegalArgumentException(formatException(exceptionMessage));
        }
    }
}
