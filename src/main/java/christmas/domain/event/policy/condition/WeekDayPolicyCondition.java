package christmas.domain.event.policy.condition;

import static christmas.domain.event.policy.condition.PolicyConditionThreshold.WEEKDAY_END;
import static christmas.domain.event.policy.condition.PolicyConditionThreshold.WEEKDAY_START;

import christmas.domain.calendar.Dates;

public final class WeekDayPolicyCondition {

    private WeekDayPolicyCondition() {
    }

    public static boolean verifyCondition(String date) {
        int dateSeqByDateKor = Dates.getDateSeqByDateKor(date);
        return WEEKDAY_START.value <= dateSeqByDateKor
                && dateSeqByDateKor <= WEEKDAY_END.value;
    }
}
