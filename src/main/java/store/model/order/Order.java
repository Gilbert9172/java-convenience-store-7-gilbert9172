package store.model.order;

import java.time.LocalDateTime;
import store.model.money.Money;
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

    public long feedBackQuantity() {
        return feedBack.getQuantity();
    }

    public Product getProduct() {
        return product;
    }

    public Quantity getOrdrerQuantity() {
        return orderQuantities.getTotal();
    }

    public Quantity getPrizeCount() {
        return orderQuantities.getPrize();
    }

    public Money getProductAmount() {
        return product.getAmount();
    }

    public Money totalPrice() {
        Quantity orderQuantity = getOrdrerQuantity();
        Money productPrice = getProductAmount();
        return productPrice.multiply(orderQuantity);
    }

    public boolean isNormalProduct() {
        return product.isNormal();
    }

    public boolean isPromotionProduct() {
        return !isNormalProduct();
    }

    public boolean hasPrize() {
        Quantity prizeCount = orderQuantities.getPrize();
        return prizeCount.biggerThan(Quantity.ZERO);
    }

    public Quantity decreaseNormalStock() {
        return feedBack.getNormalStock();
    }
}
