package store.common.exception;

public class InputPurchaseFormatException extends CustomException {

    private static final String INCORRECT_FORMAT_PURCHASE_INPUT = "[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.";

    public InputPurchaseFormatException() {
        super(INCORRECT_FORMAT_PURCHASE_INPUT);
    }
}
