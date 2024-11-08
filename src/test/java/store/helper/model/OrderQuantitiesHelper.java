package store.helper.model;

import store.model.order.OrderQuantities;
import store.model.order.Quantity;

public class OrderQuantitiesHelper {

    private OrderQuantitiesHelper() {
    }

    public static OrderQuantities mock(final int orderQuantity,
                                       final int promotionQuantity,
                                       final int normalQuantity,
                                       final int prizeQuantity) {
        return OrderQuantities.of(
                Quantity.from(orderQuantity),
                Quantity.from(promotionQuantity),
                Quantity.from(normalQuantity),
                Quantity.from(prizeQuantity)
        );
    }
}
