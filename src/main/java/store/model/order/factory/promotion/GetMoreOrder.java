package store.model.order.factory.promotion;

import java.time.LocalDateTime;
import store.model.order.Order;
import store.model.order.OrderWarning;
import store.model.order.Quantity;
import store.model.order.factory.OrderGenerator;
import store.model.product.Product;

public class GetMoreOrder implements OrderGenerator {

    @Override
    public Order generate(final Product product, final LocalDateTime orderDate, final int quantity) {
        int moreQuantity = product.grapMoreQuantity(quantity);
        return Order.of(
                product,
                Quantity.of(quantity, 0, quantity, 0),
                orderDate,
                OrderWarning.grapMore(moreQuantity));
    }
}
