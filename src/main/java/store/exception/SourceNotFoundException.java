package store.exception;

import static store.error.ErrorMessage.PRODUCT_NOT_FOUND;

public class SourceNotFoundException extends BusinessException {

    private SourceNotFoundException(final String message) {
        super(message);
    }

    public static SourceNotFoundException productNotFoundException() {
        throw new SourceNotFoundException(PRODUCT_NOT_FOUND);
    }
}
