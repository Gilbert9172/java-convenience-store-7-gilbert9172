package store.io.exception;

import static store.io.error.ErrorMessage.INVALID_ORDER_REGEX;

public class InvalidRegexException extends InputException {

    public InvalidRegexException(final String message) {
        super(message);
    }

    public static InvalidRegexException invalidOrderRegex() {
        throw new InvalidRegexException(INVALID_ORDER_REGEX);
    }
}
