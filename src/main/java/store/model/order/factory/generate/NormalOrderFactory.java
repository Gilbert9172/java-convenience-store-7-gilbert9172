package store.model.order.factory.generate;

import static store.model.order.OrderFeedBack.Type.NONE;

import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.Map;
import store.model.order.Order;
import store.model.order.OrderFeedBack.Type;
import store.model.order.factory.generate.normal.NormalOrder;
import store.model.product.Product;

public class NormalOrderFactory {

    private static final Map<Type, OrderGenerator> generatorMap = new EnumMap<>(Type.class);

    static {
        generatorMap.put(NONE, new NormalOrder());
    }

    public Order generateOrderByCondition(
            final Product product,
            final LocalDateTime orderDate,
            final int quantity) {
        OrderGenerator normalOrder = generatorMap.get(NONE);
        return normalOrder.generate(product, orderDate, quantity);
    }
}
