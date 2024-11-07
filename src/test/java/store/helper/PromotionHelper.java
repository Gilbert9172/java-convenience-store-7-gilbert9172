package store.helper;

import store.converter.TimeConverter;
import store.model.promotion.Promotion;
import store.model.promotion.PromotionId;

public class PromotionHelper {

    private PromotionHelper() {
    }

    public static Promotion mock(final long id,
                                 final String name,
                                 final int buy,
                                 final int get,
                                 final String startDate,
                                 final String endDate) {
        return Promotion.of(
                PromotionId.from(id),
                name,
                buy,
                get,
                TimeConverter.toStartDate(startDate),
                TimeConverter.toEndDate(endDate)
        );
    }
}
