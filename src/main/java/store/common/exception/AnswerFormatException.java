package store.common.exception;

public class AnswerFormatException extends CustomException {

    private static final String INCORRECT_FORMAT_MESSAGE = "[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.";

    public AnswerFormatException() {
        super(INCORRECT_FORMAT_MESSAGE);
    }
}
