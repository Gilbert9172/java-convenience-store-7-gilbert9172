package store.model.promotion;

import java.time.LocalDateTime;
import store.model.order.Quantity;

public class Promotion {

    private final PromotionId id;
    private final String title;
    private final Quantity buy;
    private final Quantity get;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    private Promotion(
            final PromotionId id,
            final String title,
            final Quantity buy,
            final Quantity get,
            final LocalDateTime startDate,
            final LocalDateTime endDate
    ) {
        this.id = id;
        this.title = title;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Promotion of(
            final PromotionId id,
            final String title,
            final Quantity buy,
            final Quantity get,
            final LocalDateTime startDate,
            final LocalDateTime endDate
    ) {
        return new Promotion(id, title, buy, get, startDate, endDate);
    }

    public boolean hasSameTitle(final String that) {
        return this.title.equals(that);
    }

    public boolean isActive(LocalDateTime now) {
        boolean isBetweenStartAndEnd = isBetween(now, startDate, endDate);
        boolean isSameWithStart = isSame(now, startDate);
        boolean isSameWithEnd = now.isEqual(endDate);
        return isBetweenStartAndEnd || isSameWithStart || isSameWithEnd;
    }

    public Quantity expectedQuantityOf(Quantity orderQuantity) {
        return orderQuantity.add(this.get);
    }

    public boolean availableOfferPrize(Quantity orderQuantity) {
        Quantity expected = expectedQuantityOf(orderQuantity);
        Quantity buyGet = buyGetQuantity();
        Quantity remainder = expected.getRemainderBy(buyGet);
        return remainder.isZero();
    }

    public boolean satisfiedMinBuy(Quantity orderQuantity) {
        return orderQuantity.boeThan(buy);
    }

    private boolean isBetween(final LocalDateTime now, final LocalDateTime start, final LocalDateTime end) {
        boolean after = now.isAfter(start);
        boolean before = now.isBefore(end);
        return after && before;
    }

    private boolean isSame(final LocalDateTime now, final LocalDateTime standard) {
        return now.isEqual(standard);
    }

    public Quantity buyGetQuantity() {
        return buy.add(get);
    }

    public Quantity getMinBuyQuantity() {
        return this.buy;
    }

    public Quantity getGet() {
        return get;
    }

    public String getTitle() {
        return title;
    }
}
