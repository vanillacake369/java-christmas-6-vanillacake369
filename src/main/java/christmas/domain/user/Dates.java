package christmas.domain.user;

public enum Dates {
    MONDAY("월요일"),
    TUESDAY("화요일"),
    WEDNESDAY("수요일"),
    THURSDAY("목요일"),
    FRIDAY("금요일"),
    SATURDAY("토요일"),
    SUNDAY("일요일");

    final String dateKor;

    Dates(String dateKor) {
        this.dateKor = dateKor;
    }

    public String getDateKor() {
        return dateKor;
    }
}
