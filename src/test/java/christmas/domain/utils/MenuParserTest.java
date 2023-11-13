package christmas.domain.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuParserTest {

    @ParameterizedTest
    @DisplayName("문자열에 대해 \",\" 을 delimiter 로 하여 각 문자 별로 파싱합니다")
    @ValueSource(strings = {"스테이크-3,크리스마스파스타-2"})
    void 콤마로_문자열_파싱(String menuInput) {
        // WHEN
        List<String> menusInput = MenuParser.parseByComma(menuInput);

        // THEN
        assertTrue(menusInput.contains("스테이크-3"));
        assertTrue(menusInput.contains("크리스마스파스타-2"));
    }
}