package store.model.order;

public class OrderFeedBack {

    public enum Type {
        OUT_OF_STOCK,
        GRAB_MORE,
        NONE
    }

    private final int quantity;
    private final Type type;

    private OrderFeedBack(final int quantity, final Type type) {
        this.quantity = quantity;
        this.type = type;
    }

    public static OrderFeedBack empty() {
        return new OrderFeedBack(0, Type.NONE);
    }

    public static OrderFeedBack grabMore(final int quantity) {
        return new OrderFeedBack(quantity, Type.GRAB_MORE);
    }

    public static OrderFeedBack outOfStock(final int quantity) {
        return new OrderFeedBack(quantity, Type.OUT_OF_STOCK);
    }

    public boolean isGrabMoreType() {
        return this.type == Type.GRAB_MORE;
    }

    public boolean isOutOfStockType() {
        return this.type == Type.OUT_OF_STOCK;
    }

    public int getAddedQuantityWith(final int value) {
        return quantity + value;
    }

    public int getQuantity() {
        return quantity;
    }
}
