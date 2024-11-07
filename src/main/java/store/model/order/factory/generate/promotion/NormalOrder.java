package store.model.order.factory.generate.promotion;

import java.time.LocalDateTime;
import store.model.order.Order;
import store.model.order.OrderWarning;
import store.model.order.Quantity;
import store.model.order.factory.generate.OrderGenerator;
import store.model.product.Product;

public class NormalOrder implements OrderGenerator {

    @Override
    public Order generate(final Product product, final LocalDateTime orderDate, final int quantity) {
        return Order.of(
                product,
                Quantity.of(quantity, quantity, 0, product.prizeQuantityOf(quantity)),
                orderDate,
                OrderWarning.empty());
    }
}
