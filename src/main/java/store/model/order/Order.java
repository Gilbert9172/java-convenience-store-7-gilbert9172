package store.model.order;

import java.time.LocalDateTime;
import store.model.product.Product;

public class Order {

    private final Product product;
    private final Quantity quantity;
    private final LocalDateTime date;
    private final OrderFeedBack feedBack;

    public Order(final Product product,
                 final Quantity quantity,
                 final LocalDateTime date,
                 final OrderFeedBack feedBack
    ) {
        this.product = product;
        this.quantity = quantity;
        this.date = date;
        this.feedBack = feedBack;
    }

    public static Order of(final Product product,
                           final Quantity quantity,
                           final LocalDateTime date,
                           final OrderFeedBack feedBack
    ) {
        return new Order(product, quantity, date, feedBack);
    }

    public void applyPositiveFeedBack() {
        quantity.updateQuantityByPositiveFeedBack(feedBack);

    }

    public void applyNegativeFeedBack() {
        quantity.updateQuantityByNegativeFeedBack(feedBack);

    }

    public boolean hasOutOfStockFeedBack() {
        return feedBack.isOutOfStockType();
    }

    public boolean hasGrapMoreFeedBack() {
        return feedBack.isGrapMoreType();
    }

    public String purchasedProductName() {
        return product.getName();
    }

    public int feedBackQuantity() {
        return feedBack.getQuantity();
    }
}
