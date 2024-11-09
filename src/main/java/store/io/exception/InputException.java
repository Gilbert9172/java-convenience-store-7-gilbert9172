package store.io.exception;

public class InputException extends IllegalArgumentException {

    private static final String ERROR_PREFIX = "[ERROR] ";

    public InputException(final String message) {
        super(ERROR_PREFIX + message);
    }
}
