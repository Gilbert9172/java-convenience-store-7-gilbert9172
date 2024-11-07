package store.model.order;

public class OrderFeedBack {

    public enum Type {
        OUT_OF_STOCK,
        GRAP_MORE,
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

    public static OrderFeedBack grapMore(final int quantity) {
        return new OrderFeedBack(quantity, Type.GRAP_MORE);
    }

    public static OrderFeedBack outOfStock(final int quantity) {
        return new OrderFeedBack(quantity, Type.OUT_OF_STOCK);
    }

    public boolean isGrapMoreType() {
        return this.type == Type.GRAP_MORE;
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
