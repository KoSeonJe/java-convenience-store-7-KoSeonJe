package store.view;

import camp.nextstep.edu.missionutils.Console;

public class InputConsoleView implements InputView {

    private static final String REQUIRE_INPUT_ITEM_MESSAGE = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";

    @Override
    public String requireInputItem() {
        println(REQUIRE_INPUT_ITEM_MESSAGE);
        return Console.readLine();
    }

    private void println(String message) {
        System.out.println(message);
    }
}
