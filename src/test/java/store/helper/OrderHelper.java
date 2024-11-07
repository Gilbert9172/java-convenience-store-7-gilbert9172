package store.helper;

import store.converter.TimeConverter;
import store.model.order.Order;
import store.model.order.OrderWarning;
import store.model.order.Quantity;
import store.model.product.Product;

public class OrderHelper {

    private OrderHelper() {
    }

    public static Order mock(final Product product,
                             final Quantity quantity,
                             final String dateTime,
                             final OrderWarning orderWarning) {
        return Order.of(
                product,
                quantity,
                TimeConverter.toEndDate(dateTime),
                orderWarning
        );
    }
}
