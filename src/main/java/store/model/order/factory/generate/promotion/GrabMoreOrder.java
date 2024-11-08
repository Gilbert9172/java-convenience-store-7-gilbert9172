package store.model.order.factory.generate.promotion;

import java.time.LocalDateTime;
import store.model.order.Order;
import store.model.order.OrderFeedBack;
import store.model.order.OrderQuantities;
import store.model.order.Quantity;
import store.model.order.factory.generate.OrderGenerateHandler;
import store.model.product.Product;

public class GrabMoreOrder implements OrderGenerateHandler {

    @Override
    public Order generate(final Product product, final LocalDateTime orderDate, final Quantity quantity) {
        Quantity moreQuantity = product.grabMoreQuantity(quantity);
        Quantity promotionQuantity = product.promotionQuantityForGetMore(quantity);
        Quantity prizeQuantity = product.prizeQuantityOf(quantity);

        return Order.of(
                product,
                OrderQuantities.of(quantity, promotionQuantity, quantity.minus(promotionQuantity), prizeQuantity),
                orderDate,
                OrderFeedBack.grabMore(moreQuantity));
    }
}
