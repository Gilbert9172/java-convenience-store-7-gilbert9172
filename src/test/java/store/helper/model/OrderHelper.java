package store.helper.model;

import store.converter.TimeConverter;
import store.model.order.Order;
import store.model.order.OrderFeedBack;
import store.model.order.OrderQuantities;
import store.model.product.Product;

public class OrderHelper {

    private OrderHelper() {
    }

    public static Order mock(final Product product,
                             final OrderQuantities orderQuantities,
                             final String dateTime,
                             final OrderFeedBack orderFeedBack) {
        return Order.of(
                product,
                orderQuantities,
                TimeConverter.toEndDate(dateTime),
                orderFeedBack
        );
    }
}
