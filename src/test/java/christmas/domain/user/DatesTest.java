package christmas.domain.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.calendar.Dates;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DatesTest {
    @Test
    @DisplayName("요일에 따른 날짜 시퀀스를 찾습니다.")
    void 요일에_따른_날짜시퀀스_해피케이스() {
        // GIVEN
        String dateKor = "금요일";
        // WHEN
        int dateSeqByDateKor = Dates.getDateSeqByDateKor(dateKor);
        // THEN
        assertEquals(5, dateSeqByDateKor);
    }
}