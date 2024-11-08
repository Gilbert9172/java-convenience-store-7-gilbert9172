package store.model.discount;

import store.model.money.Money;
import store.model.order.Orders;

public interface DiscountPolicy {
    Money applyDiscount(Orders orders);
}
