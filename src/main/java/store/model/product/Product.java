package store.model.product;

import static store.exception.OutOfStockException.outOfStock;
import static store.model.order.Quantity.ZERO;

import java.time.LocalDateTime;
import store.model.money.Money;
import store.model.order.Quantity;
import store.model.promotion.Promotion;

public class Product {

    private final String name;
    private final Money amount;
    private Quantity stock;
    private final Promotion promotion;

    private Product(final String name,
                    final Money amount,
                    final Quantity stock,
                    final Promotion promotion) {
        this.name = name;
        this.amount = amount;
        this.stock = stock;
        this.promotion = promotion;
    }

    public static Product of(final String name,
                             final Money amount,
                             final Quantity stock,
                             final Promotion promotion) {
        return new Product(name, amount, stock, promotion);
    }

    public boolean hasSameName(final String name) {
        return name.equals(this.name);
    }

    public boolean inStock() {
        return stock.biggerThan(ZERO);
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

    public boolean promotionStockCannotHandle(final Quantity orderQuantity) {
        //Quantity outOfPromotionStockQuantity = outOfPromotionStockQuantityOf(orderQuantity);
        return orderQuantity.biggerThan(this.stock);
    }

    public Quantity outOfPromotionStockQuantityOf(final Quantity orderQuantity) {
        Quantity availablePromotionStock = availablePromotionStock();
        return orderQuantity.minus(availablePromotionStock);
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
        Quantity get = promotion.getGet();
        Quantity buy = promotion.getMinBuyQuantity();
        Quantity remainder = orderQuantity.add(get).getRemainderBy(buyGetCount);

        boolean boeThanBuyQuantity = orderQuantity.boeThan(buy);

        //Quantity remainder = orderQuantity.getRemainderBy(buyGetCount);

        return boeThanBuyQuantity && remainder.equals(ZERO);
    }

    public void decreasedStock(final Quantity quantity) {
        Quantity remainingStock = stock.minus(quantity);
        if (remainingStock.LowerThan(ZERO)) {
            throw outOfStock();
        }
        this.stock = remainingStock;
    }

    public Quantity currentStock() {
        return stock;
    }

    public String getName() {
        return name;
    }

    public Money getAmount() {
        return amount;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public boolean isNormal() {
        return promotion == null;
    }

    @Override
    public String toString() {
        String promotionTitle = null;
        if (promotion != null) {
            promotionTitle = promotion.getTitle();
        }

        return String.format("%s,%d,%d,%s",
                name, amount.getAmount(), stock.getValue(), promotionTitle);
    }
}
