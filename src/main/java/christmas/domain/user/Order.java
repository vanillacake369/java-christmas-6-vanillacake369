package christmas.domain.user;

import christmas.domain.menu.Menu;
import java.util.HashMap;

public record Order(HashMap<Menu, Integer> orderMenus) {
}
