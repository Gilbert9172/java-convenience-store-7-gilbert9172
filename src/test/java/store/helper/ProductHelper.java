package store.helper;

import store.model.money.Money;
import store.model.order.Quantity;
import store.model.product.Product;
import store.model.promotion.Promotion;

public class ProductHelper {

    private ProductHelper() {
    }

    public static Product mock(final String name,
                               final long amount,
                               final int quantity,
                               final Promotion promotion) {
        return Product.of(name, Money.from(amount), Quantity.of(quantity), promotion);
    }
}
