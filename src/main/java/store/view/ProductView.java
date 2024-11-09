package store.view;

import static java.util.Objects.nonNull;

import store.model.money.Money;
import store.model.order.Quantity;
import store.model.product.Product;
import store.model.promotion.Promotion;

public class ProductView {

    private final String name;
    private final String amount;
    private final String stock;
    private final String promotionTitle;

    private ProductView(final String name,
                        final String amount,
                        final String stock,
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
        return new ProductView(
                productName,
                amount.toString(),
                stockByCondition(stock),
                promotionTitleByCondition(promotion)
        );
    }

    private static String stockByCondition(Quantity stock) {
        if (stock.equals(Quantity.ZERO)) {
            return "재고 없음";
        }
        return stock.toString();
    }

    private static String promotionTitleByCondition(Promotion promotion) {
        if (nonNull(promotion)) {
            return promotion.getTitle();
        }
        return "";
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    public String getStock() {
        return stock;
    }

    public String getPromotionTitle() {
        return promotionTitle;
    }
}
