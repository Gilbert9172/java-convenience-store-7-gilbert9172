package store.exception;

public class SourceNotFoundException extends BusinessException {

    private SourceNotFoundException(final String message) {
        super(message);
    }

    public static SourceNotFoundException notFoundException(String message) {
        throw new SourceNotFoundException(message);
    }
}
