package store.model.product;

import static store.model.order.Quantity.ONE;
import static store.model.order.Quantity.ZERO;

import java.time.LocalDateTime;
import store.model.money.Money;
import store.model.order.Quantity;
import store.model.promotion.Promotion;

public class Product {

    private final String name;
    private final Money amount;
    private final Quantity stock;
    private final Promotion promotion;

    private Product(
            final String name,
            final Money amount,
            final Quantity stock,
            final Promotion promotion
    ) {
        this.name = name;
        this.amount = amount;
        this.stock = stock;
        this.promotion = promotion;
    }

    public static Product of(
            final String name,
            final Money amount,
            final Quantity stock,
            final Promotion promotion
    ) {
        return new Product(name, amount, stock, promotion);
    }

    public boolean hasSameName(final String name) {
        return name.equals(this.name);
    }

    public boolean isAvailable(final LocalDateTime now) {
        if (promotion == null) {
            return true;
        }
        return promotion.isActive(now);
    }

    public boolean promotionApplied() {
        return promotion != null;
    }

    public boolean promotionNotApplied() {
        return !promotionApplied();
    }

    public boolean stockIsLowerThan(final Quantity orderQuantity) {
        Quantity outOfStockQuantity = outOfStockQuantity(orderQuantity);
        return outOfStockQuantity.biggerThan(ZERO);
    }

    public Quantity outOfStockQuantity(final Quantity orderQuantity) {
        Quantity availableStock = availablePromotionStock();
        return orderQuantity.minus(availableStock);
    }

    // 제공 가능한 프로모션 재고
    public Quantity availablePromotionStock() {
        Quantity buyGetCount = promotion.buyGetQuantity();
        Quantity availableSet = stock.divide(buyGetCount);
        return availableSet.multiply(buyGetCount);
    }

    public Quantity promotionQuantityForGetMore(final Quantity quantity) {
        Quantity buyGetCount = promotion.buyGetQuantity();
        Quantity divided = quantity.divide(buyGetCount);
        return divided.multiply(buyGetCount);
    }

    public Quantity prizeQuantityOf(final Quantity orderQuantity) {
        Quantity buyGetCount = promotion.buyGetQuantity();
        return orderQuantity.divide(buyGetCount);
    }

    public Quantity grabMoreQuantity(final Quantity orderQuantity) {
        Quantity buyGetCount = promotion.buyGetQuantity();
        Quantity remainder = orderQuantity.getRemainderBy(buyGetCount);
        return buyGetCount.minus(remainder);
    }

    public boolean hasChanceToGetPrize(final Quantity orderQuantity) {
        Quantity buyGetCount = promotion.buyGetQuantity();
        boolean moreThanOne = orderQuantity.biggerThan(ONE);
        Quantity remainder = orderQuantity.getRemainderBy(buyGetCount);
        return moreThanOne && remainder.notEquals(ZERO);
    }

    public Quantity currentStock() {
        return stock;
    }

    public String getName() {
        return name;
    }
}
