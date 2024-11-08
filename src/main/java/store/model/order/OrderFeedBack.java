package store.model.order;

public class OrderFeedBack {

    public enum Type {
        OUT_OF_STOCK,
        GRAB_MORE,
        NONE
    }

    private final Quantity quantity;
    private final Type type;

    private OrderFeedBack(final Quantity quantity, final Type type) {
        this.quantity = quantity;
        this.type = type;
    }

    public static OrderFeedBack empty() {
        return new OrderFeedBack(Quantity.ZERO, Type.NONE);
    }

    public static OrderFeedBack grabMore(final Quantity quantity) {
        return new OrderFeedBack(quantity, Type.GRAB_MORE);
    }

    public static OrderFeedBack outOfStock(final Quantity quantity) {
        return new OrderFeedBack(quantity, Type.OUT_OF_STOCK);
    }

    public boolean isGrabMoreType() {
        return this.type == Type.GRAB_MORE;
    }

    public boolean isOutOfStockType() {
        return this.type == Type.OUT_OF_STOCK;
    }

    public Quantity getAddedQuantityWith(final Quantity that) {
        return quantity.add(that);
    }

    public int getQuantity() {
        return quantity.getValue();
    }
}
