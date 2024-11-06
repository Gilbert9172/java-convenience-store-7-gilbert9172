package store.model.promotion;

import static java.util.Objects.isNull;
import static store.exception.ShouldNotBeNullException.idIsNull;

import java.util.Arrays;
import java.util.Objects;

public class PromotionId {

    enum Type {
        N_PLUS_ONE("탄산2+1", PromotionId.from(1L)),
        MD("MD추천상품", PromotionId.from(2L)),
        SURPRISE("반짝할인", PromotionId.from(3L)),
        ;

        final String title;
        final PromotionId fixedId;

        Type(final String title, final PromotionId fixedId) {
            this.title = title;
            this.fixedId = fixedId;
        }

        public static PromotionId getFixedIdBy(String title) {
            return Arrays.stream(Type.values())
                    .filter(type -> Objects.equals(type.title, title))
                    .findFirst()
                    .map(type -> type.fixedId)
                    .orElse(null);
        }
    }

    private final Long id;

    private PromotionId(final Long id) {
        this.id = id;
    }

    public static PromotionId findByType(String title) {
        return Type.getFixedIdBy(title);
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
