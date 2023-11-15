package christmas.domain.user;

public enum EventTime {
    YEAR(2023),
    MONTH(12);

    public final int time;

    EventTime(int time) {
        this.time = time;
    }
}
