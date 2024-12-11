package store.model.order.factory.generate;

import static store.model.order.OrderFeedBack.Type.GRAB_MORE;
import static store.model.order.OrderFeedBack.Type.NONE;
import static store.model.order.OrderFeedBack.Type.OUT_OF_STOCK;

import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.Map;
import store.model.order.Order;
import store.model.order.OrderFeedBack.Type;
import store.model.order.Quantity;
import store.model.order.factory.generate.promotion.GrabMoreOrder;
import store.model.order.factory.generate.promotion.NormalOrder;
import store.model.order.factory.generate.promotion.OutOfStockOrder;
import store.model.product.Product;

public class PromotionOrderFactory {

    private static final Map<Type, OrderGenerateHandler> generatorMap = new EnumMap<>(Type.class);

    static {
        generatorMap.put(OUT_OF_STOCK, new OutOfStockOrder());
        generatorMap.put(GRAB_MORE, new GrabMoreOrder());
        generatorMap.put(NONE, new NormalOrder());
    }

    public Order generateOrderByCondition(
            final Product promotionProduct,
            final LocalDateTime orderDate,
            final Quantity orderQuantity
    ) {
        if (promotionProduct.cannotHandle(orderQuantity)) {
            OrderGenerateHandler outOfStockOrder = generatorMap.get(OUT_OF_STOCK);
            return outOfStockOrder.generate(promotionProduct, orderDate, orderQuantity);
        }

        if (promotionProduct.canOfferPrizeFrom(orderQuantity)) {
            OrderGenerateHandler getMoreOrder = generatorMap.get(GRAB_MORE);
            return getMoreOrder.generate(promotionProduct, orderDate, orderQuantity);
        }

        OrderGenerateHandler normalOrder = generatorMap.get(NONE);
        return normalOrder.generate(promotionProduct, orderDate, orderQuantity);
    }
}
