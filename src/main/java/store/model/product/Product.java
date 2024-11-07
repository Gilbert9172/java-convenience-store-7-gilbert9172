package store.model.product;

import java.time.LocalDateTime;
import store.model.money.Money;
import store.model.promotion.Promotion;

public class Product {

    private final String name;
    private final Money amount;
    private final int quantity;
    private final Promotion promotion;

    private Product(
            final String name,
            final Money amount,
            final int quantity,
            final Promotion promotion
    ) {
        this.name = name;
        this.amount = amount;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public static Product of(
            final String name,
            final Money amount,
            final int quantity,
            final Promotion promotion
    ) {
        return new Product(name, amount, quantity, promotion);
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

    public boolean outOfStock(final int orderQuantity) {
        int outOfStockQuantity = outOfStockQuantity(orderQuantity);
        return outOfStockQuantity > 0;
    }

    public int outOfStockQuantity(final int orderQuantity) {
        int availableStock = promotionQuantity();
        return orderQuantity - availableStock;
    }

    public int promotionQuantity() {
        int buyGetCount = promotion.buyGetQuantity();
        return (this.quantity / buyGetCount) * buyGetCount;
    }

    public int normalQuantity(final int quantity) {
        int i = promotionQuantityForGetMore(quantity);
        return quantity - i;
    }

    public int promotionQuantityForGetMore(final int quantity) {
        int buyGetCount = promotion.buyGetQuantity();
        return (quantity / buyGetCount) * buyGetCount;
    }

    public int prizeQuantityOf(final int orderQuantity) {
        int buyGetCount = promotion.buyGetQuantity();
        return orderQuantity / buyGetCount;
    }

    public int grapMoreQuantity(final int orderQuantity) {
        int buyGetCount = promotion.buyGetQuantity();
        return buyGetCount - (orderQuantity % buyGetCount);
    }

    public boolean hasChanceToGetPrize(final int orderQuantity) {
        int buyGetCount = promotion.buyGetQuantity();
        return orderQuantity >= 1 && orderQuantity % buyGetCount != 0;
    }

    public int getQuantity() {
        return quantity;
    }
}
