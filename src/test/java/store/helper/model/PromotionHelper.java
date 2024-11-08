package store.helper.model;

import store.converter.TimeConverter;
import store.model.order.Quantity;
import store.model.promotion.Promotion;
import store.model.promotion.PromotionId;

public class PromotionHelper {

    private PromotionHelper() {
    }

    public static Promotion twoPlusOnePromotion(final String start, final String end) {
        return PromotionHelper.mock(
                1,
                "탄산2+1",
                2,
                1,
                start,
                end);
    }

    public static Promotion mdRecommandPromotion(final String start, final String end) {
        return PromotionHelper.mock(
                2,
                "MD추천상품",
                1,
                1,
                start,
                end);
    }

    public static Promotion surprisePromotion(final String start, final String end) {
        return PromotionHelper.mock(
                2,
                "반짝할인",
                1,
                1,
                start,
                end);
    }

    private static Promotion mock(final long id,
                                  final String name,
                                  final int buy,
                                  final int get,
                                  final String startDate,
                                  final String endDate) {
        return Promotion.of(
                PromotionId.from(id),
                name,
                Quantity.from(buy),
                Quantity.from(get),
                TimeConverter.toStartDate(startDate),
                TimeConverter.toEndDate(endDate)
        );
    }
}
