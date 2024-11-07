package store.model.order.factory.generate;

import static store.model.order.OrderFeedBack.Type.GRAP_MORE;
import static store.model.order.OrderFeedBack.Type.NONE;
import static store.model.order.OrderFeedBack.Type.OUT_OF_STOCK;

import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.Map;
import store.model.order.Order;
import store.model.order.OrderFeedBack.Type;
import store.model.order.factory.generate.promotion.GetMoreOrder;
import store.model.order.factory.generate.promotion.NormalOrder;
import store.model.order.factory.generate.promotion.OutOfStockOrder;
import store.model.product.Product;

public class PromotionOrderFactory {

    private static final Map<Type, OrderGenerator> generatorMap = new EnumMap<>(Type.class);

    static {
        generatorMap.put(OUT_OF_STOCK, new OutOfStockOrder());
        generatorMap.put(GRAP_MORE, new GetMoreOrder());
        generatorMap.put(NONE, new NormalOrder());
    }

    public Order generateOrderByCondition(
            final Product product,
            final LocalDateTime orderDate,
            final int quantity) {
        if (product.outOfStock(quantity)) {
            OrderGenerator outOfStockOrder = generatorMap.get(OUT_OF_STOCK);
            return outOfStockOrder.generate(product, orderDate, quantity);
        }

        if (product.hasChanceToGetPrize(quantity)) {
            OrderGenerator getMoreOrder = generatorMap.get(GRAP_MORE);
            return getMoreOrder.generate(product, orderDate, quantity);
        }

        OrderGenerator normalOrder = generatorMap.get(NONE);
        return normalOrder.generate(product, orderDate, quantity);
    }
}
