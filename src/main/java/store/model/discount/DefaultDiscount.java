package store.model.discount;

import store.model.money.Money;
import store.model.order.Orders;

public class DefaultDiscount implements DiscountPolicy {

    @Override
    public Money applyDiscount(final Orders orders) {
        return Money.ZERO;
    }
}
