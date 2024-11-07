package store.exception;

import static store.error.ErrorMessage.OUT_OF_STOCK;

public class OutOfStockException extends BusinessException {

    private OutOfStockException(final String message) {
        super(message);
    }

    public static OutOfStockException outOfStock() {
        throw new OutOfStockException(OUT_OF_STOCK);
    }
}
