package store.model.order;

import java.time.LocalDateTime;
import store.model.product.Product;

public class Order {

    private final Product product;
    private final OrderQuantities orderQuantities;
    private final LocalDateTime date;
    private final OrderFeedBack feedBack;

    public Order(final Product product,
                 final OrderQuantities orderQuantities,
                 final LocalDateTime date,
                 final OrderFeedBack feedBack
    ) {
        this.product = product;
        this.orderQuantities = orderQuantities;
        this.date = date;
        this.feedBack = feedBack;
    }

    public static Order of(final Product product,
                           final OrderQuantities orderQuantities,
                           final LocalDateTime date,
                           final OrderFeedBack feedBack
    ) {
        return new Order(product, orderQuantities, date, feedBack);
    }

    public void applyPositiveFeedBack() {
        orderQuantities.updateQuantityByPositiveFeedBack(feedBack);

    }

    public void applyNegativeFeedBack() {
        orderQuantities.updateQuantityByNegativeFeedBack(feedBack);

    }

    public boolean hasOutOfStockFeedBack() {
        return feedBack.isOutOfStockType();
    }

    public boolean hasGrabMoreFeedBack() {
        return feedBack.isGrabMoreType();
    }

    public String purchasedProductName() {
        return product.getName();
    }

    public int feedBackQuantity() {
        return feedBack.getQuantity();
    }

    public Product getProduct() {
        return product;
    }

    public Quantity getOrdrerQuantity() {
        return orderQuantities.getTotal();
    }
}
