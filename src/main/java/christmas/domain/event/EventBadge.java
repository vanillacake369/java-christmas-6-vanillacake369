package christmas.domain.event;

public enum EventBadge {
    STAR("별"),
    TREE("트리"),
    SANTA("산타");

    final String badgeKor;

    EventBadge(String badgeKor) {
        this.badgeKor = badgeKor;
    }

    public static EventBadge getBadge(Long eventPriceSum) {
        if (eventPriceSum >= 20_000L) {
            return SANTA;
        }
        if (eventPriceSum >= 10_000L) {
            return TREE;
        }
        if (eventPriceSum >= 50L) {
            return STAR;
        }

        return null;
    }
}
