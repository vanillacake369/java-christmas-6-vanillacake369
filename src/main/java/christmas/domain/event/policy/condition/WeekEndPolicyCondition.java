package christmas.domain.event.policy.condition;

import static christmas.domain.event.policy.condition.PolicyConditionThreshold.WEEKEND_START;

import christmas.domain.calendar.Dates;

public final class WeekEndPolicyCondition {

    private WeekEndPolicyCondition() {
    }

    public static boolean verifyCondition(String date) {
        int dateSeqByDateKor = Dates.getDateSeqByDateKor(date);
        return WEEKEND_START.value <= dateSeqByDateKor;
    }
}
