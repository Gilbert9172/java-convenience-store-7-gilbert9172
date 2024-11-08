package store.model.discount;

import store.model.money.Money;
import store.model.order.Orders;

public class PromotionDiscount implements DiscountPolicy {

    @Override
    public Money applyDiscount(final Orders orders) {
        return orders.totalPromotionProductsAmount();
    }
}
