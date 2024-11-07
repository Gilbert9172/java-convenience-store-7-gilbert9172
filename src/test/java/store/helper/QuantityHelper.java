package store.helper;

import store.model.order.Quantity;

public class QuantityHelper {

    private QuantityHelper() {
    }

    public static Quantity mock(final int orderQuantity,
                                final int promotionQuantity,
                                final int normalQuantity,
                                final int prizeQuantity) {
        return Quantity.of(orderQuantity, promotionQuantity, normalQuantity, prizeQuantity);
    }
}
