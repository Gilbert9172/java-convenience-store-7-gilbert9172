package store.model.order.factory.modify;

import static store.model.order.OrderFeedBack.Type.GRAB_MORE;
import static store.model.order.OrderFeedBack.Type.OUT_OF_STOCK;

import java.util.EnumMap;
import java.util.Map;
import store.model.order.Order;
import store.model.order.OrderFeedBack.Type;

public class OrderFeedBackHandlerFactory {

    private static final Map<Type, OrderFeedbackHandler> modifierMap = new EnumMap<>(Type.class);

    static {
        modifierMap.put(OUT_OF_STOCK, new OutOfStockFeedbackHandler());
        modifierMap.put(GRAB_MORE, new GetMoreFeedbackHandler());
    }

    public void updateOrderByCondition(Order order, UserFeedBack flag) {
        if (order.hasOutOfStockFeedBack()) {
            OrderFeedbackHandler outOfStockModifier = modifierMap.get(OUT_OF_STOCK);
            outOfStockModifier.modify(order, flag);
        }

        if (order.hasGrabMoreFeedBack()) {
            OrderFeedbackHandler getMoreModifier = modifierMap.get(GRAB_MORE);
            getMoreModifier.modify(order, flag);
        }
    }
}
