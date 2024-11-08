package store.exception;

import static store.error.ErrorMessage.INVALID_QUANTITY;
import static store.error.ErrorMessage.QUANTITY_CANNOT_BE_MINUS;

public class InvalidQuantityException extends BusinessException {
    private InvalidQuantityException(String message) {
        super(message);
    }

    public static InvalidQuantityException invalidQuantity() {
        throw new InvalidQuantityException(INVALID_QUANTITY);
    }

    public static InvalidQuantityException minusQuantity() {
        throw new InvalidQuantityException(QUANTITY_CANNOT_BE_MINUS);
    }
}
