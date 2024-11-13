package store.model.order;

import java.util.List;
import java.util.Objects;

public class Quantity {

    public static Quantity ZERO = Quantity.from(0);
    public static Quantity ONE = Quantity.from(1);

    private final long value;

    private Quantity(final long value) {
        this.value = value;
    }

    public static Quantity from(final long value) {
        return new Quantity(value);
    }

    public boolean LowerThan(final Quantity that) {
        return this.value < that.value;
    }

    public boolean biggerThan(final Quantity that) {
        return this.value > that.value;
    }

    public boolean boeThan(final Quantity that) {
        return this.value >= that.value;
    }

    public static Quantity addAll(List<Quantity> quantities) {
        long allQuantity = quantities.stream()
                .mapToLong(quantity -> quantity.value)
                .sum();
        return Quantity.from(allQuantity);
    }

    public Quantity add(final Quantity that) {
        return Quantity.from(this.value + that.value);
    }

    public Quantity divide(final Quantity that) {
        return Quantity.from(this.value / that.value);
    }

    public Quantity multiply(final Quantity that) {
        return Quantity.from(this.value * that.value);
    }

    public Quantity minus(final Quantity that) {
        return Quantity.from(this.value - that.value);
    }

    public Quantity getRemainderBy(final Quantity that) {
        long remainder = this.value % that.value;
        return Quantity.from(remainder);
    }

    public boolean isZero() {
        return this.value == ZERO.value;
    }

    public long getValue() {
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
        return this.value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.value);
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
