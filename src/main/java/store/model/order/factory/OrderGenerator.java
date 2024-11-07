package store.model.order.factory;

import java.time.LocalDateTime;
import store.model.order.Order;
import store.model.product.Product;

public interface OrderGenerator {
    Order generate(final Product product, final LocalDateTime orderDate, final int quantity);
}
