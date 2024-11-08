package store.model.discount;

import java.math.BigDecimal;
import store.model.money.Money;
import store.model.order.Orders;

public class MemberShipDiscount implements DiscountPolicy {

    private final BigDecimal DISCOUNT_RATIO = new BigDecimal("0.3");
    private final Money MAX_DISCOUNT_AMOUNT = Money.from(8000L);

    @Override
    public Money applyDiscount(final Orders orders) {
        Money totalAmount = orders.totalNormalProductsAmount();
        Money discountAppliedMoney = calculateDiscountAppliedAmount(totalAmount);
        return Money.min(discountAppliedMoney, MAX_DISCOUNT_AMOUNT);
    }

    private Money calculateDiscountAppliedAmount(final Money totalPrice) {
        BigDecimal price = totalPrice.toBigDecimal();
        BigDecimal discountedPrice = price.multiply(DISCOUNT_RATIO);
        return Money.valueOf(discountedPrice);
    }
}
