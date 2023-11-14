package christmas.domain.menu;

public enum MenuType {
    APPETIZER("에피타이저"),
    MAIN("메인"),
    DESERT("디저트"),
    BEVERAGE("음료");

    final String menuTypeKor;

    MenuType(String menuTypeKor) {
        this.menuTypeKor = menuTypeKor;
    }
}
