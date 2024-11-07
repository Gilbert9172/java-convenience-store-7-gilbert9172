package store.model.order;

public class Quantity {

    private final int orderQuantity;
    private final int promotionQuantity;
    private final int normalQuantity;
    private final int prizeQuantity;

    private Quantity(
            final int orderQuantity,
            final int promotionQuantity,
            final int normalQuantity,
            final int prizeQuantity
    ) {
        this.orderQuantity = orderQuantity;
        this.promotionQuantity = promotionQuantity;
        this.normalQuantity = normalQuantity;
        this.prizeQuantity = prizeQuantity;
    }

    public static Quantity of(
            final int orderQuantity,
            final int promotionQuantity,
            final int normalQuantity,
            final int prizeQuantity
    ) {
        return new Quantity(orderQuantity, promotionQuantity, normalQuantity, prizeQuantity);
    }
}
