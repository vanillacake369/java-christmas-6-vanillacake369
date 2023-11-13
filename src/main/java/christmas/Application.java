package christmas;

import christmas.domain.view.InputView;

public class Application {
    public static void main(String[] args) {
        int day = InputView.getDay();
        System.out.println(day);
    }
}