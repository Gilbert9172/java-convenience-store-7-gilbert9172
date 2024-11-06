package store.exception;

import static store.error.ErrorMessage.MONEY_CANNOT_BE_MINUS;

public class InvalidMoneyException extends BusinessException {
    private InvalidMoneyException(final String message) {
        super(message);
    }

    public static InvalidMoneyException minusAmount() {
        throw new InvalidMoneyException(MONEY_CANNOT_BE_MINUS);
    }
}
