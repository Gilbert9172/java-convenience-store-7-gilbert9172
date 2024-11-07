package store.model.order;

import java.time.LocalDateTime;
import store.model.product.Product;

public class Order {

    private final Product product;
    private final Quantity quantity;
    private final LocalDateTime dateTime;
    private final OrderWarning warning;

    public Order(final Product product,
                 final Quantity quantity,
                 final LocalDateTime dateTime,
                 final OrderWarning warning
    ) {
        this.product = product;
        this.quantity = quantity;
        this.dateTime = dateTime;
        this.warning = warning;
    }

    public static Order of(final Product product,
                           final Quantity quantity,
                           final LocalDateTime dateTime,
                           final OrderWarning warning
    ) {
        return new Order(product, quantity, dateTime, warning);
    }

    public void updateQuantityYesCondition() {
        quantity.updateQuantitiesForYesResponse(warning);

    }

    public void updateQuantityNoCondition() {
        quantity.updateQuantitiesForNoResponse(warning);

    }

    public boolean isOutOfStockType() {
        return warning.isOutOfStockType();
    }

    public boolean isGetMoreType() {
        return warning.isGetMoreType();
    }
}
