package store.io.validator.order;

import static java.util.Objects.isNull;
import static store.exception.ShouldNotBeNullException.nullArg;
import static store.io.exception.InvalidRegexException.invalidOrderRegex;
import static store.io.exception.InvalidRegexException.invalidUserFeedBackRegex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {

    private final static Pattern ORDER_REGEX_PATTERN = Pattern.compile(
            "\\[[가-힣]+-[1-9]\\d*\\](,(\\[[가-힣]+-[1-9]\\d*\\]))*");
    private final static Pattern USER_FEED_BACK_PATTERN = Pattern.compile("[Y|N]");

    private InputValidator() {
    }

    public static void validateOrderInput(final String source) {
        validateIsNullOrEmpty(source);
        if (isNotMatched(ORDER_REGEX_PATTERN, source)) {
            throw invalidOrderRegex();
        }
    }

    public static void validateUserFeedBack(final String source) {
        validateIsNullOrEmpty(source);
        if (isNotMatched(USER_FEED_BACK_PATTERN, source)) {
            throw invalidUserFeedBackRegex();
        }
    }

    private static void validateIsNullOrEmpty(final String source) {
        if (isNull(source) || source.isEmpty() || source.isBlank()) {
            throw nullArg();
        }

    }

    private static boolean isNotMatched(final Pattern pattern, final String source) {
        Matcher matcher = pattern.matcher(source);
        return !matcher.matches();
    }
}
