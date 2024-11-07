package store.model.order;

public class OrderWarning {

    public enum Type {
        OUT_OF_STOCK,
        GET_MORE,
        NONE
    }

    private final int quantity;
    private final Type type;

    private OrderWarning(final int quantity, final Type type) {
        this.quantity = quantity;
        this.type = type;
    }

    public static OrderWarning empty() {
        return new OrderWarning(0, Type.NONE);
    }

    public static OrderWarning grapMore(final int quantity) {
        return new OrderWarning(quantity, Type.GET_MORE);
    }

    public static OrderWarning outOfStock(final int quantity) {
        return new OrderWarning(quantity, Type.OUT_OF_STOCK);
    }

    public boolean isGetMoreType() {
        return this.type == Type.GET_MORE;
    }

    public boolean isOutOfStockType() {
        return this.type == Type.OUT_OF_STOCK;
    }

    public int getAddedQuantityWith(final int value) {
        return quantity + value;
    }
}
