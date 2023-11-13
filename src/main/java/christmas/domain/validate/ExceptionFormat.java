package christmas.domain.validate;

public final class ExceptionFormat {
    private ExceptionFormat() {
    }

    public static String formatException(String exceptionMessage) {
        return String.format("[ERROR] %s", exceptionMessage);
    }
}
