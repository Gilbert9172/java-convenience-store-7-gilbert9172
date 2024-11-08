package store.model.order;

import static store.exception.InvalidQuantityException.minusQuantity;

import java.util.List;
import java.util.Objects;

public class Quantity {

    public static Quantity ZERO = Quantity.of(0);
    public static Quantity ONE = Quantity.of(1);

    private final int value;

    private Quantity(final int value) {
        this.value = value;
    }

    public static Quantity of(final int value) {
        validateIsMinus(value);
        return new Quantity(value);
    }

    public boolean isLowerThan(final Quantity that) {
        return this.value < that.value;
    }

    public boolean biggerThan(final Quantity that) {
        return this.value > that.value;
    }

    public static Quantity addAll(List<Quantity> quantities) {
        int allQuantity = quantities.stream()
                .mapToInt(q -> q.value)
                .sum();
        return Quantity.of(allQuantity);
    }

    public Quantity add(final Quantity that) {
        return Quantity.of(this.value + that.value);
    }

    public Quantity divide(final Quantity that) {
        return Quantity.of(this.value / that.value);
    }

    public Quantity multiply(final Quantity that) {
        return Quantity.of(this.value * that.value);
    }

    public Quantity minus(final Quantity that) {
        return Quantity.of(this.value - that.value);
    }

    public Quantity getRemainderBy(final Quantity that) {
        int remainder = this.value % that.value;
        return Quantity.of(remainder);
    }

    private static void validateIsMinus(final int value) {
        if (value < 0) {
            throw minusQuantity();
        }
    }

    public boolean notEquals(Quantity quantity) {
        return this.value != quantity.value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Quantity that = (Quantity) obj;
        return (long) this.value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.value);
    }
}
