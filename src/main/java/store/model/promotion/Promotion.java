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

    public boolean hasSameId(PromotionId that) {
        return this.id.equals(that);
    }

    public boolean hasSameTitle(final String that) {
        return this.title.equals(that);
    }

    public boolean isActive(LocalDateTime now) {
        boolean after = now.isAfter(startDate);
        boolean before = now.isBefore(endDate);
        return after && before || now.isEqual(startDate) || now.isEqual(endDate);
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
