package store.view;

import static java.util.Objects.nonNull;

import store.model.money.Money;
import store.model.order.Quantity;
import store.model.product.Product;
import store.model.promotion.Promotion;

public class ProductView {

    private final String name;
    private final String amount;
    private final long stock;
    private final String promotionTitle;

    private ProductView(final String name,
                        final String amount,
                        final long stock,
                        final String promotionTitle) {
        this.name = name;
        this.amount = amount;
        this.stock = stock;
        this.promotionTitle = promotionTitle;
    }

    public static ProductView from(Product product) {
        String productName = product.getName();
        Money amount = product.getAmount();
        Quantity stock = product.currentStock();
        Promotion promotion = product.getPromotion();
        String promotionTitle = "없음";
        if (nonNull(promotion)) {
            promotionTitle = promotion.getTitle();
        }
        return new ProductView(
                productName,
                amount.toString(),
                stock.getValue(),
                promotionTitle
        );
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    public long getStock() {
        return stock;
    }

    public String getPromotionTitle() {
        return promotionTitle;
    }
}
