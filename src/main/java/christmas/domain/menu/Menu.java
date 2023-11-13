package christmas.domain.menu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Menu {
    MUSHROOM_SOUP(MenuType.APPETIZER, "양송이수프", 6_000L),
    TAPAS(MenuType.APPETIZER, "타파스", 8_000L),
    CAESAR_SALAD(MenuType.APPETIZER, "시저샐러드", 8_000L),

    T_BONE_STEAK(MenuType.MAIN, "티본스테이크", 55_000L),
    BARBECUE_RIBS(MenuType.MAIN, "바비큐립", 54_000L),
    SEAFOOD_PASTA(MenuType.MAIN, "해산물파스타", 35_000L),
    CHRISTMAS_PASTA(MenuType.MAIN, "크리스마스파스타", 25_000L),

    CHOCOLATE_CAKE(MenuType.DESERT, "초코케이크", 15_000L),
    ICECREAM(MenuType.DESERT, "아이스크림", 5_000L),

    ZERO_COKE(MenuType.BEVERAGE, "제로콜라", 3_000L),
    RED_WINE(MenuType.BEVERAGE, "레드와인", 6_000L),
    CHAMPAGNE(MenuType.BEVERAGE, "샴페인", 25_000L),
    ;

    final MenuType menuType;
    final String menuName;
    final Long price;

    Menu(MenuType menuType, String menuName, Long price) {
        this.menuType = menuType;
        this.menuName = menuName;
        this.price = price;
    }

    public static List<String> getMenuNames() {
        return Arrays.stream(values())
                .map(menu -> menu.menuName)
                .collect(Collectors.toList());
    }

    public static Menu findByMenuName(String menuName) {
        return Arrays.stream(values())
                .filter(menu -> menu.menuName.equals(menuName))
                .findFirst().orElse(null);
    }
}
