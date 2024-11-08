package store.helper;

import store.model.order.OrderFeedBack;
import store.model.order.Quantity;

public class OrderWarningHelper {

    private OrderWarningHelper() {
    }

    public static OrderFeedBack grabMore(Quantity quantity) {
        return OrderFeedBack.grabMore(quantity);
    }

    public static OrderFeedBack outOfStock(Quantity quantity) {
        return OrderFeedBack.outOfStock(quantity);
    }
}
