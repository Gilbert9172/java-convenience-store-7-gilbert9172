package store.model.promotion;

import java.time.LocalDateTime;

public class Promotion {

    private final PromotionId id;
    private final String name;
    private final int buy;
    private final int get;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    private Promotion(
            final PromotionId id,
            final String name,
            final int buy,
            final int get,
            final LocalDateTime startDate,
            final LocalDateTime endDate
    ) {
        this.id = id;
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Promotion of(
            final PromotionId id,
            final String name,
            final int buy,
            final int get,
            final LocalDateTime startDate,
            final LocalDateTime endDate
    ) {
        return new Promotion(id, name, buy, get, startDate, endDate);
    }

    public boolean hasSameId(PromotionId that) {
        return this.id.equals(that);
    }

    public boolean isActive(LocalDateTime now) {
        boolean after = now.isAfter(startDate);
        boolean before = now.isBefore(endDate);
        return after && before;
    }

    public int buyGetQuantity() {
        return buy + get;
    }

    public int getGetQuantity() {
        return get;
    }
}
