package store.helper.model;

import store.model.money.Money;
import store.model.order.Quantity;
import store.model.product.Product;
import store.model.product.ProductId;
import store.model.promotion.Promotion;

public class ProductHelper {

    private ProductHelper() {
    }

    public static Product mock(
            final Long id,
            final String name,
            final long amount,
            final int quantity,
            final Promotion promotion) {
        return Product.of(ProductId.from(id), name, Money.from(amount), Quantity.from(quantity), promotion);
    }
}
