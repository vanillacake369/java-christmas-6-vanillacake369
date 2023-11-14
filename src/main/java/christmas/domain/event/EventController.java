package christmas.domain.event;

import christmas.domain.user.User;
import christmas.domain.view.OutputView;

public class EventController {
    private final User user;
    private final EventService eventService;

    public EventController(User user) {
        this.user = user;
        this.eventService = new EventService(user);
    }

    public void runEvent() {
        // 이벤트 적용 결과
        EventResult eventResult = new EventResult();
        // 이벤트 적용 가능하다면
        if (EventJoinableChecker.isJoinable(user)) {
            // 이벤트 적용
            eventResult = eventService.applyEvents(user);
        }

        // 이벤트 적용 결과 출력
        OutputView.showEventResult(user, eventResult);
    }
}