package store.model.product;

import static java.util.Objects.isNull;
import static store.exception.ShouldNotBeNullException.idIsNull;

public class ProductId {

    private final Long value;

    private ProductId(final Long value) {
        this.value = value;
    }

    public static ProductId from(final Long value) {
        validateNotNull(value);
        return new ProductId(value);
    }

    private static void validateNotNull(final Long value) {
        if (isNull(value)) {
            throw idIsNull();
        }
    }
}
