package christmas.domain.user;

import static christmas.domain.menu.Menu.BARBECUE_RIBS;
import static christmas.domain.menu.Menu.CHOCOLATE_CAKE;
import static christmas.domain.menu.Menu.T_BONE_STEAK;
import static christmas.domain.menu.Menu.ZERO_COKE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserServiceTest {
    private final InputStream stdin = System.in;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @AfterEach
    void tearDown() {
        userService = null;
        System.setIn(stdin);
    }

    private void readInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @ParameterizedTest
    @ValueSource(strings = "3\n티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1\n")
    @DisplayName("방문 날짜와 주문메뉴,주문개수를 입력받아 사용자 불변객체를 생성합니다.")
    void 사용자_불변객체_생성(String input) {
        //  WHEN
        readInput(input);
        User user = userService.createUser();

        //  THEN
        assertEquals(user.visitDay().day(), 3);
        assertEquals(user.visitDay().date(), "일요일");
        assertTrue(user.order().orderMenus().keySet()
                .containsAll(List.of(T_BONE_STEAK, BARBECUE_RIBS, CHOCOLATE_CAKE, ZERO_COKE)));
        assertTrue(user.order().orderMenus().values()
                .containsAll(List.of(1, 1, 2, 1)));
    }

}