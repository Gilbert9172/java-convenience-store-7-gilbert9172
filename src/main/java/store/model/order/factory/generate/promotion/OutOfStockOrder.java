package store.model.order.factory.generate.promotion;

import java.time.LocalDateTime;
import store.model.order.Order;
import store.model.order.OrderFeedBack;
import store.model.order.OrderQuantities;
import store.model.order.Quantity;
import store.model.order.factory.generate.OrderGenerateHandler;
import store.model.product.Product;

public class OutOfStockOrder implements OrderGenerateHandler {

    @Override
    public Order generate(final Product product, final LocalDateTime orderDate, final Quantity quantity) {
        Quantity decreaseFromPromotionStock = product.outOfPromotionStockQuantity();
        Quantity decreaseFromNormalStock = product.outOfNormalStockQuantity(quantity);
        Quantity promotionQuantity = product.availablePromotionStock();
        Quantity prizeCount = product.prizeQuantityOf(promotionQuantity);
        return Order.of(
                product,
                OrderQuantities.of(quantity, promotionQuantity, quantity.minus(promotionQuantity), prizeCount),
                orderDate,
                OrderFeedBack.outOfStock(decreaseFromPromotionStock, decreaseFromNormalStock)
        );
    }
}
