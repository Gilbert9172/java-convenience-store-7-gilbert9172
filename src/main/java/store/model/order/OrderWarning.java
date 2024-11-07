package store.model.order;

public class OrderWarning {

    enum Type {
        MORE,
        LESS,
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
        return new OrderWarning(quantity, Type.LESS);
    }

    public static OrderWarning outOfStock(final int quantity) {
        return new OrderWarning(quantity, Type.MORE);
    }
}
