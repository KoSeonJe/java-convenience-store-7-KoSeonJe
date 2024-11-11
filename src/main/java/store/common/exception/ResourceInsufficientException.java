package store.common.exception;

public class ResourceInsufficientException extends CustomException {

    private static final String NO_ENOUGH_PRODUCT_MESSAGE = "[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.";

    public ResourceInsufficientException() {
        super(NO_ENOUGH_PRODUCT_MESSAGE);
    }
}
