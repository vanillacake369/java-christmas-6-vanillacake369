package christmas.domain.utils;

import java.util.Arrays;
import java.util.List;

public final class MenuParser {
    private MenuParser() {
    }

    public static List<String> parseByComma(String menuInput) {
        return Arrays.asList(menuInput.split(","));
    }
}
