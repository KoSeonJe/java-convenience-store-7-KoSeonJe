package store.common.exception;

public class ResourceNotFoundException extends CustomException{

    private static final String NO_EXIST_PRODUCT_MESSAGE = "[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.";

    public ResourceNotFoundException() {
        super(NO_EXIST_PRODUCT_MESSAGE);
    }
}
