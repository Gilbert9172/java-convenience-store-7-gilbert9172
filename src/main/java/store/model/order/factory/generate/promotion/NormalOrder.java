package store.model.order.factory.generate.promotion;

import java.time.LocalDateTime;
import store.model.order.Order;
import store.model.order.OrderFeedBack;
import store.model.order.OrderQuantities;
import store.model.order.Quantity;
import store.model.order.factory.generate.OrderGenerateHandler;
import store.model.product.Product;

public class NormalOrder implements OrderGenerateHandler {

    @Override
    public Order generate(final Product product, final LocalDateTime orderDate, final Quantity quantity) {
        return Order.of(
                product,
                OrderQuantities.of(quantity, quantity, Quantity.ZERO, product.prizeQuantityOf(quantity)),
                orderDate,
                OrderFeedBack.empty());
    }
}
