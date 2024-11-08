package store.model.order.factory.generate.normal;

import java.time.LocalDateTime;
import store.model.order.Order;
import store.model.order.OrderFeedBack;
import store.model.order.Quantity;
import store.model.order.factory.generate.OrderGenerateHandler;
import store.model.product.Product;

public class NormalOrder implements OrderGenerateHandler {

    @Override
    public Order generate(final Product product, final LocalDateTime orderDate, final int quantity) {
        return Order.of(
                product,
                Quantity.of(quantity, 0, quantity, 0),
                orderDate,
                OrderFeedBack.empty());
    }
}
