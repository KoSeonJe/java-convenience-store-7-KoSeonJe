package store.presentation.view.console;

import camp.nextstep.edu.missionutils.Console;
import store.presentation.view.InputView;

public class InputConsoleView implements InputView {

    private static final String REQUIRE_INPUT_ITEM_MESSAGE = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";
    private static final String CONFIRM_ADDITIONAL_ITEM_MESSAGE = "현재 %s은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)";


    @Override
    public String requireInputItem() {
        println(REQUIRE_INPUT_ITEM_MESSAGE);
        return Console.readLine();
    }

    @Override
    public String confirmAdditionalItem(String name) {
        System.out.printf(CONFIRM_ADDITIONAL_ITEM_MESSAGE, name);
        return Console.readLine();
    }

    private void println(String message) {
        System.out.println(message);
    }
}
