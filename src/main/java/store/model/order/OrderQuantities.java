package store.model.order;

import static store.model.order.Quantity.ONE;
import static store.model.order.Quantity.ZERO;

import java.util.Objects;

public class OrderQuantities {

    private Quantity total;
    private Quantity promotionStock;
    private Quantity normalStock;
    private Quantity prize;

    private OrderQuantities(final Quantity total,
                            final Quantity promotionStock,
                            final Quantity normalStock,
                            final Quantity prize) {
        this.total = total;
        this.promotionStock = promotionStock;
        this.normalStock = normalStock;
        this.prize = prize;
    }

    public static OrderQuantities of(final Quantity total,
                                     final Quantity promotionStock,
                                     final Quantity normalStock,
                                     final Quantity prize) {
        return new OrderQuantities(total, promotionStock, normalStock, prize);
    }

    public void updateQuantityByPositiveFeedBack(final OrderFeedBack warning) {
        if (warning.isGrabMoreType()) {
            this.total = warning.getAddedQuantityWith(total);
            this.promotionStock = this.total;
            this.normalStock = ZERO;
            this.prize = prize.add(ONE);
        }
    }

    public void updateQuantityByNegativeFeedBack(final OrderFeedBack warning) {
        if (warning.isOutOfStockType()) {
            this.total = total.minus(normalStock);
            this.normalStock = ZERO;
        }
    }

    public Quantity getTotal() {
        return total;
    }

    public Quantity getPrize() {
        return prize;
    }

    public Quantity getNormalStock() {
        return normalStock;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                total.getValue(),
                promotionStock.getValue(),
                normalStock.getValue(),
                prize.getValue()
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OrderQuantities orderQuantities = (OrderQuantities) obj;
        return total.equals(orderQuantities.total) &&
                promotionStock.equals(orderQuantities.promotionStock) &&
                normalStock.equals(orderQuantities.normalStock) &&
                prize.equals(orderQuantities.prize);
    }
}
