package store.exception;

import static store.error.ErrorMessage.ID_SHOULD_NOT_BE_NULL;
import static store.error.ErrorMessage.SHOULD_NOT_BE_NULL;

public class ShouldNotBeNullException extends BusinessException {
    private ShouldNotBeNullException(String message) {
        super(message);
    }

    public static ShouldNotBeNullException idIsNull() {
        throw new ShouldNotBeNullException(ID_SHOULD_NOT_BE_NULL);
    }

    public static ShouldNotBeNullException nullArg() {
        throw new ShouldNotBeNullException(SHOULD_NOT_BE_NULL);
    }
}
