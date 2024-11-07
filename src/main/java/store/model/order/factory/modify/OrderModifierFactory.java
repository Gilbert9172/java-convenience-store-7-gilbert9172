package store.model.order.factory.modify;

import static store.model.order.OrderWarning.Type.GET_MORE;
import static store.model.order.OrderWarning.Type.OUT_OF_STOCK;

import java.util.EnumMap;
import java.util.Map;
import store.model.order.Order;
import store.model.order.OrderWarning.Type;

public class OrderModifierFactory {

    private static final Map<Type, OrderModifier> modifierMap = new EnumMap<>(Type.class);

    static {
        modifierMap.put(OUT_OF_STOCK, new OutOfStockModifier());
        modifierMap.put(GET_MORE, new GetMoreModifier());
    }

    public void updateOrderByCondition(Order order, OrderModifyFlag flag) {
        if (order.isOutOfStockType()) {
            OrderModifier outOfStockModifier = modifierMap.get(OUT_OF_STOCK);
            outOfStockModifier.modify(order, flag);
        }

        if (order.isGetMoreType()) {
            OrderModifier getMoreModifier = modifierMap.get(GET_MORE);
            getMoreModifier.modify(order, flag);
        }
    }
}
