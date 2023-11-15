package christmas.domain.view;

import christmas.domain.calendar.EventTime;
import christmas.domain.event.EventResultDTO;
import christmas.domain.event.policy.EventBadge;
import christmas.domain.menu.Menu;
import java.text.DecimalFormat;
import java.util.HashMap;

public final class OutputView {
    private OutputView() {
    }

    public static void showEventResult(int visitDay, EventResultDTO eventResultDto) {
        showVisitDay(visitDay);
        System.out.println();
        showOrderMenus(eventResultDto);
        System.out.println();
        showEventPreviousPriceSum(eventResultDto);
        System.out.println();
        showGift(eventResultDto);
        System.out.println();
        showBenefits(eventResultDto);
        System.out.println();
        showDiscountPriceSum(eventResultDto);
        System.out.println();
        showEventAfterPriceSum(eventResultDto);
        System.out.println();
        showEventBadge(eventResultDto);
    }

    private static void showVisitDay(int visitDay) {
        System.out.printf("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n", EventTime.MONTH.getTime(), visitDay);
    }

    private static void showOrderMenus(EventResultDTO eventResultDto) {
        System.out.println("<주문 메뉴>");
        HashMap<Menu, Integer> orderMenus = eventResultDto.getOrder().orderMenus();
        for (Menu menu : orderMenus.keySet()) {
            System.out.printf("%s %s개%n", menu.getMenuName(), orderMenus.get(menu));
        }
    }

    private static void showEventPreviousPriceSum(EventResultDTO eventResultDto) {
        System.out.println("<할인 전 총주문 금액>");
        Long eventPreviousPriceSum = eventResultDto.getEventPreviousPriceSum();
        String formattedPreviousPriceSum = new DecimalFormat("#,###").format(eventPreviousPriceSum);
        System.out.println(formattedPreviousPriceSum + "원");
    }

    private static void showGift(EventResultDTO eventResultDto) {
        System.out.println("<증정 메뉴>");
        HashMap<Menu, Integer> giveawayMenus = eventResultDto.getGiveawayMenus();
        if (giveawayMenus.size() > 0) {
            for (Menu menu : giveawayMenus.keySet()) {
                System.out.printf("%s %d개%n", menu.getMenuName(), giveawayMenus.get(menu));
            }
            return;
        }
        System.out.println("없음");
    }

    private static void showBenefits(EventResultDTO eventResultDto) {
        System.out.println("<혜택 내역>");
        // !! 구현 요망 !!
        // !! 구현 요망 !!
        // !! 구현 요망 !!
        // !! 구현 요망 !!
        System.out.println("없음");
    }

    private static void showDiscountPriceSum(EventResultDTO eventResultDto) {
        System.out.println("<총혜택 금액>");
        if (eventResultDto.getBenefitPriceSum() > 0) {
            System.out.println(eventResultDto.getBenefitPriceSum());
            return;
        }
        System.out.println("없음");
    }

    private static void showEventAfterPriceSum(EventResultDTO eventResultDto) {
        System.out.println("<할인 후 예상 결제 금액>");
        Long eventAfterPriceSum = eventResultDto.getEventAfterPriceSum();
        String formattedAfterPriceSum = new DecimalFormat("#,###").format(eventAfterPriceSum);
        System.out.println(formattedAfterPriceSum + "원");
    }

    private static void showEventBadge(EventResultDTO eventResultDto) {
        System.out.printf("<%d월 이벤트 배지>\n", EventTime.MONTH.getTime());
        EventBadge eventBadge = eventResultDto.getEventBadge();
        if (eventBadge != null) {
            System.out.println(eventBadge.getBadgeKor());
            return;
        }
        System.out.println("없음");
    }
}
