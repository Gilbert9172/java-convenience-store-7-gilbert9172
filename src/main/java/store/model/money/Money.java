package store.model.money;

import static store.exception.InvalidMoneyException.minusAmount;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import store.model.order.Quantity;

public class Money {

    public static final Money ZERO = Money.from(0);

    private final long amount;

    private Money(final long amount) {
        this.amount = amount;
    }

    public static Money from(long amount) {
        validateIsMinus(amount);
        return new Money(amount);
    }

    public static Money addAll(List<Money> monies) {
        long totalAmount = monies.stream()
                .mapToLong(money -> money.amount)
                .sum();
        return Money.from(totalAmount);
    }

    public static Money min(Money m1, Money m2) {
        long minAmount = Math.min(m1.amount, m2.amount);
        return Money.from(minAmount);
    }

    public static Money valueOf(BigDecimal amount) {
        return Money.from(amount.longValue());
    }

    public Money add(Money that) {
        return Money.from(this.amount + that.amount);
    }

    public Money minus(Money that) {
        return Money.from(this.amount - that.amount);
    }

    public Money multiply(Quantity quantity) {
        long multipliedValue = this.amount * quantity.getValue();
        return Money.from(multipliedValue);
    }

    private static void validateIsMinus(long amount) {
        if (amount < 0) {
            throw minusAmount();
        }
    }

    public BigDecimal toBigDecimal() {
        return BigDecimal.valueOf(this.amount);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Money that = (Money) obj;
        return this.amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.amount);
    }
}
