package store.common.exception;

public class FileLoadException extends CustomException {

    private static final String FILE_LOAD_ERROR = "[ERROR] File 데이터를 불러오는 데 실패하였습니다.";

    public FileLoadException() {
        super(FILE_LOAD_ERROR);
    }
}
