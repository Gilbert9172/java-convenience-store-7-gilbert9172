package store.io.exception;

import static store.io.error.ErrorMessage.INVALID_ORDER_REGEX;
import static store.io.error.ErrorMessage.INVALID_USER_FEED_BACK_REGEX;

public class InvalidRegexException extends InputException {

    private InvalidRegexException(final String message) {
        super(message);
    }

    public static InvalidRegexException invalidOrderRegex() {
        throw new InvalidRegexException(INVALID_ORDER_REGEX);
    }

    public static InvalidRegexException invalidUserFeedBackRegex() {
        throw new InvalidRegexException(INVALID_USER_FEED_BACK_REGEX);
    }
}
