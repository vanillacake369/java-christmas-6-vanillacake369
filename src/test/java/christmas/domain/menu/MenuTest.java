package christmas.domain.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {
    @Test
    @DisplayName("메뉴명으로 메뉴를 가져옵니다")
    void 메뉴명으로_메뉴_찾기() {
        // GIVEN
        String tbone = "티본스테이크";

        // WHEN
        Menu byMenuName = Menu.findByMenuName(tbone);

        // THEN
        assertEquals(byMenuName.menuName, tbone);
    }
}