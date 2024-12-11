package store.model.order;

public class OrderFeedBack {

    public enum Type {
        OUT_OF_STOCK,
        GRAB_MORE,
        NONE
    }

    private final Quantity promotionStock;
    private final Quantity normalStock;
    private final Type type;

    private OrderFeedBack(final Quantity promotionStock, final Quantity normalStock, final Type type) {
        this.promotionStock = promotionStock;
        this.normalStock = normalStock;
        this.type = type;
    }

    public static OrderFeedBack empty() {
        return new OrderFeedBack(Quantity.ZERO, Quantity.ZERO, Type.NONE);
    }

    public static OrderFeedBack grabMore(final Quantity normalStock) {
        return new OrderFeedBack(Quantity.ZERO, normalStock, Type.GRAB_MORE);
    }

    public static OrderFeedBack outOfStock(final Quantity promotionStock, final Quantity normalStock) {
        return new OrderFeedBack(promotionStock, normalStock, Type.OUT_OF_STOCK);
    }

    public boolean isGrabMoreType() {
        return this.type == Type.GRAB_MORE;
    }

    public boolean isOutOfStockType() {
        return this.type == Type.OUT_OF_STOCK;
    }

    public Quantity getAddedQuantityWith(final Quantity that) {
        return normalStock.add(that);
    }

    public Quantity totalQuantity() {
        return normalStock.add(promotionStock);
    }

    public Quantity getNormalStock() {
        return normalStock;
    }
}
