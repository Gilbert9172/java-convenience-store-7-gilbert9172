package store.io.validator.order;

import static java.util.Objects.isNull;
import static store.exception.ShouldNotBeNullException.nullArg;
import static store.io.exception.InvalidRegexException.invalidOrderRegex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderInputValidator {

    private final static Pattern pattern = Pattern.compile(
            "\\[[가-힣]+-[1-9]\\d*\\](,(\\[[가-힣]+-[1-9]\\d*\\]))*");

    private OrderInputValidator() {
    }

    public static void validate(final String source) {

        if (isNull(source) || source.isEmpty() || source.isBlank()) {
            throw nullArg();
        }

        if (isNotMatched(source)) {
            throw invalidOrderRegex();
        }
    }


    private static boolean isNotMatched(final String source) {
        Matcher matcher = pattern.matcher(source);
        return !matcher.matches();
    }
}
