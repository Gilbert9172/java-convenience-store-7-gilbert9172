package store.model.order.factory.generate;

import java.time.LocalDateTime;
import store.model.order.Order;
import store.model.order.Quantity;
import store.model.product.Product;

public interface OrderGenerateHandler {
    Order generate(final Product product, final LocalDateTime orderDate, final Quantity quantity);
}
