package store.helper;

import store.model.order.OrderQuantities;
import store.model.order.Quantity;

public class QuantityHelper {

    private QuantityHelper() {
    }

    public static OrderQuantities mock(final int orderQuantity,
                                       final int promotionQuantity,
                                       final int normalQuantity,
                                       final int prizeQuantity) {
        return OrderQuantities.of(
                Quantity.of(orderQuantity),
                Quantity.of(promotionQuantity),
                Quantity.of(normalQuantity),
                Quantity.of(prizeQuantity)
        );
    }
}
