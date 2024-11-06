package store.exception;

public class BusinessException extends RuntimeException {

    private static final String ERROR_PREFIX = "[ERROR] ";

    public BusinessException(final String message) {
        super(ERROR_PREFIX + message);
    }
}
