package store.model.money;

import static store.exception.InvalidMoneyException.minusAmount;

public class Money {

    private final long amount;

    private Money(final long amount) {
        this.amount = amount;
    }

    public static Money from(long amount) {
        validateIsMinus(amount);
        return new Money(amount);
    }

    private static void validateIsMinus(long amount) {
        if (amount < 0) {
            throw minusAmount();
        }
    }
}
