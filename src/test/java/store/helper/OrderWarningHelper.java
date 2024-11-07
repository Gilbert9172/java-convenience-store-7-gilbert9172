package store.helper;

import store.model.order.OrderWarning;

public class OrderWarningHelper {

    private OrderWarningHelper() {
    }

    public static OrderWarning grapMore(int quantity) {
        return OrderWarning.grapMore(quantity);
    }

    public static OrderWarning outOfStock(int quantity) {
        return OrderWarning.outOfStock(quantity);
    }
}
