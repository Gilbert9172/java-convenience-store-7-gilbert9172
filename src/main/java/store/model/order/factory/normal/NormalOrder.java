package store.model.order.factory.normal;

import java.time.LocalDateTime;
import store.model.order.Order;
import store.model.order.OrderWarning;
import store.model.order.Quantity;
import store.model.order.factory.OrderGenerator;
import store.model.product.Product;

public class NormalOrder implements OrderGenerator {

    @Override
    public Order generate(final Product product, final LocalDateTime orderDate, final int quantity) {
        return Order.of(
                product,
                Quantity.of(quantity, 0, quantity, 0),
                orderDate,
                OrderWarning.empty());
    }
}
