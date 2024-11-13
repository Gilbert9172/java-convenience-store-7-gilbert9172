package store.model.promotion;

import static java.util.Objects.isNull;
import static store.exception.ShouldNotBeNullException.idIsNull;

import java.util.Objects;

public class PromotionId {

    private final Long id;

    private PromotionId(final Long id) {
        this.id = id;
    }

    public static PromotionId from(final Long id) {
        validateIsNull(id);
        return new PromotionId(id);
    }

    private static void validateIsNull(final Long id) {
        if (isNull(id)) {
            throw idIsNull();
        }
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PromotionId that = (PromotionId) obj;
        return (long) this.id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }
}
