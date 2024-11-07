package store.exception;

import static store.error.ErrorMessage.INVALID_QUANTITY;

public class InvalidQuantityException extends BusinessException {
    private InvalidQuantityException(String message) {
        super(message);
    }

    public static InvalidQuantityException invalidQuantity() {
        throw new InvalidQuantityException(INVALID_QUANTITY);
    }
}
