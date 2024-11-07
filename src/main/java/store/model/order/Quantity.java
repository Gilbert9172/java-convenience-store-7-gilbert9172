package store.model.order;

import java.util.Objects;

public class Quantity {

    private int orderQuantity;
    private int promotionQuantity;
    private int normalQuantity;
    private int prizeQuantity;

    private Quantity(final int orderQuantity,
                     final int promotionQuantity,
                     final int normalQuantity,
                     final int prizeQuantity) {
        this.orderQuantity = orderQuantity;
        this.promotionQuantity = promotionQuantity;
        this.normalQuantity = normalQuantity;
        this.prizeQuantity = prizeQuantity;
    }

    public static Quantity of(final int orderQuantity,
                              final int promotionQuantity,
                              final int normalQuantity,
                              final int prizeQuantity) {
        return new Quantity(orderQuantity, promotionQuantity, normalQuantity, prizeQuantity);
    }

    public void updateQuantityByPositiveFeedBack(final OrderFeedBack warning) {
        if (warning.isGrapMoreType()) {
            this.orderQuantity = warning.getAddedQuantityWith(orderQuantity);
            this.promotionQuantity = this.orderQuantity;
            this.normalQuantity = 0;
            this.prizeQuantity++;
        }
    }

    public void updateQuantityByNegativeFeedBack(final OrderFeedBack warning) {
        if (warning.isOutOfStockType()) {
            this.orderQuantity -= this.normalQuantity;
            this.normalQuantity = 0;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderQuantity, promotionQuantity, normalQuantity, prizeQuantity);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Quantity quantity = (Quantity) obj;
        return orderQuantity == quantity.orderQuantity &&
                promotionQuantity == quantity.promotionQuantity &&
                normalQuantity == quantity.normalQuantity &&
                prizeQuantity == quantity.prizeQuantity;
    }
}
