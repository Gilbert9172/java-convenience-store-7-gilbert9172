package store.io.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import store.io.exception.InvalidRegexException;
import store.io.validator.order.OrderInputValidator;

public class OrderInputValidatorTest {

    @DisplayName("주문 regex InvalidRegexException test")
    @ParameterizedTest
    @CsvSource(
            value = {"[콜라-10],[물-10],", "[콜라--10],[물-10]", "[콜라-0]", "[cola-10]", "[-10]", "[콜라]", "[콜라-]", "[10]"},
            delimiter = '|'
    )
    void validateOrderRegex(String source) {
        assertThrows(
                InvalidRegexException.class,
                () -> OrderInputValidator.validate(source)
        );
    }

    @DisplayName("주문 regex normal case test")
    @ParameterizedTest
    @CsvSource(
            value = {"[콜라-10],[물-10]", "[콜라-10]"},
            delimiter = '|'
    )
    void normalCase(String source) {
        assertDoesNotThrow(() -> OrderInputValidator.validate(source));
    }
}
