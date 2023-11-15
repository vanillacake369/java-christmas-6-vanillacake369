package christmas.domain.view;

import christmas.domain.calendar.EventTime;
import christmas.domain.event.EventBadge;
import christmas.domain.event.EventResultDTO;
import christmas.domain.event.policy.event.EventPolicy;
import christmas.domain.menu.Menu;
import java.text.DecimalFormat;
import java.util.HashMap;

public final class OutputView {
    private OutputView() {
    }

    public static void showEventResult(EventResultDTO eventResultDto) {
        showVisitDay(eventResultDto.getDay());
        System.out.println();
        showOrderMenus(eventResultDto.getOrderMenus());
        System.out.println();
        showEventPreviousPriceSum(eventResultDto.getEventPreviousPriceSum());
        System.out.println();
        showGift(eventResultDto.getGiveawayMenus());
        System.out.println();
        showBenefits(eventResultDto.getAppliedEventPolicies());
        System.out.println();
        showDiscountPriceSum(eventResultDto.getBenefitPriceSum());
        System.out.println();
        showEventAfterPriceSum(eventResultDto.getEventAfterPriceSum());
        System.out.println();
        showEventBadge(eventResultDto.getEventBadge());
    }

    private static void showVisitDay(int visitDay) {
        System.out.printf("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n", EventTime.MONTH.getTime(), visitDay);
    }

    private static void showOrderMenus(HashMap<Menu, Integer> orderMenus) {
        System.out.println("<주문 메뉴>");
        for (Menu menu : orderMenus.keySet()) {
            System.out.printf("%s %s개%n", menu.getMenuName(), orderMenus.get(menu));
        }
    }

    private static void showEventPreviousPriceSum(Long eventPreviousPriceSum) {
        System.out.println("<할인 전 총주문 금액>");
        String formattedPreviousPriceSum = new DecimalFormat("#,###").format(eventPreviousPriceSum);
        System.out.println(formattedPreviousPriceSum + "원");
    }

    private static void showGift(HashMap<Menu, Integer> giveawayMenus) {
        System.out.println("<증정 메뉴>");
        if (giveawayMenus.size() > 0) {
            for (Menu menu : giveawayMenus.keySet()) {
                System.out.printf("%s %d개%n", menu.getMenuName(), giveawayMenus.get(menu));
            }
            return;
        }
        System.out.println("없음");
    }

    private static void showBenefits(HashMap<EventPolicy, Long> appliedEventPolicies) {
        System.out.println("<혜택 내역>");
        if (appliedEventPolicies.size() > 0) {
            appliedEventPolicies.forEach((eventPolicy, price) -> System.out.printf("%s : -%s원%n",
                    eventPolicy.getPolicyName(),
                    new DecimalFormat("#,###")
                            .format(price)));
            return;
        }
        System.out.println("없음");
    }

    private static void showDiscountPriceSum(Long benefitPriceSum) {
        System.out.println("<총혜택 금액>");

        if (benefitPriceSum > 0) {
            System.out.printf("-%s원%n", new DecimalFormat("#,###")
                    .format(benefitPriceSum));
            return;
        }
        System.out.println("없음");
    }

    private static void showEventAfterPriceSum(Long eventAfterPriceSum) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf("%s원%n", new DecimalFormat("#,###")
                .format(eventAfterPriceSum));
    }

    private static void showEventBadge(EventBadge eventBadge) {
        System.out.printf("<%d월 이벤트 배지>\n", EventTime.MONTH.getTime());
        if (eventBadge != null) {
            System.out.println(eventBadge.getBadgeKor());
            return;
        }
        System.out.println("없음");
    }
}
