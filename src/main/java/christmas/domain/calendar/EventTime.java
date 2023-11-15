package christmas.domain.calendar;

public enum EventTime {
    YEAR(2023),
    MONTH(12);

    public final int time;

    EventTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }
}
