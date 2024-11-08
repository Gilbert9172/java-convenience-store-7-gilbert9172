package store.helper;

import store.model.order.OrderFeedBack;

public class OrderWarningHelper {

    private OrderWarningHelper() {
    }

    public static OrderFeedBack grapMore(int quantity) {
        return OrderFeedBack.grabMore(quantity);
    }

    public static OrderFeedBack outOfStock(int quantity) {
        return OrderFeedBack.outOfStock(quantity);
    }
}
