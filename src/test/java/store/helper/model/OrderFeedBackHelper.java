package store.helper.model;

import store.model.order.OrderFeedBack;
import store.model.order.Quantity;

public class OrderFeedBackHelper {

    private OrderFeedBackHelper() {
    }

    public static OrderFeedBack empty() {
        return OrderFeedBack.empty();
    }

    public static OrderFeedBack grabMore(final long quantity) {
        return OrderFeedBack.grabMore(Quantity.from(quantity));
    }

    public static OrderFeedBack outOfStock(final long quantity) {
        return OrderFeedBack.outOfStock(Quantity.from(quantity));
    }
}
