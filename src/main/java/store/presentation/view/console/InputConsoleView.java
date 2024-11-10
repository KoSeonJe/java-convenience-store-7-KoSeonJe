package store.presentation.view.console;

import camp.nextstep.edu.missionutils.Console;
import store.presentation.view.InputView;

public class InputConsoleView implements InputView {

    private static final String REQUIRE_INPUT_ITEM_MESSAGE = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";
    private static final String CONFIRM_ADDITIONAL_ITEM_MESSAGE = "현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)";
    private static final String CONFIRM_PURCHASE_ORIGINAL_PRICE_MESSAGE = "현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)";
    private static final String CONFIRM_APPLY_MEMBERSHIP_MESSAGE = "멤버십 할인을 받으시겠습니까? (Y/N)";
    private static final String LINE_BREAKER = System.lineSeparator();

    @Override
    public String requireInputItem() {
        println(REQUIRE_INPUT_ITEM_MESSAGE);
        return Console.readLine();
    }

    @Override
    public String confirmAdditionalItem(String name, int getCount) {
        System.out.printf(LINE_BREAKER + CONFIRM_ADDITIONAL_ITEM_MESSAGE + LINE_BREAKER, name, getCount);
        return Console.readLine();
    }

    @Override
    public String confirmOriginalPrice(String name, int quantityDifference) {
        System.out.printf(LINE_BREAKER + CONFIRM_PURCHASE_ORIGINAL_PRICE_MESSAGE + LINE_BREAKER, name, quantityDifference);
        return Console.readLine();
    }

    @Override
    public String confirmApplyMembership() {
        println(LINE_BREAKER + CONFIRM_APPLY_MEMBERSHIP_MESSAGE);
        return Console.readLine();
    }

    private void println(String message) {
        System.out.println(message);
    }
}
