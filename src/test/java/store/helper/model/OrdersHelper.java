package store.helper.model;

import java.util.List;
import store.model.order.Order;
import store.model.order.Orders;
import store.model.product.Product;
import store.model.promotion.Promotion;

public class OrdersHelper {

    private OrdersHelper() {
    }

    public static Orders testSet1() {
        Promotion twoPlusOnePromotion = PromotionHelper.twoPlusOnePromotion("2024-01-01", "2024-12-31");
        Promotion mdRecommandPromotion = PromotionHelper.mdRecommandPromotion("2024-01-01", "2024-12-31");
        Promotion surprisePromotion = PromotionHelper.surprisePromotion("2024-01-01", "2024-12-31");

        Product product1 = ProductHelper.mock(1L, "콜라", 1000, 7, twoPlusOnePromotion);
        Product product2 = ProductHelper.mock(2L, "오렌지주스", 1800, 9, mdRecommandPromotion);
        Product product3 = ProductHelper.mock(3L, "물", 500, 10, null);
        Product product4 = ProductHelper.mock(4L, "감자칩", 1500, 5, surprisePromotion);

        Order order1 = OrderHelper.mock(
                product1,
                OrderQuantitiesHelper.mock(10, 6, 4, 2),
                "2024-10-01",
                OrderFeedBackHelper.outOfStock(1, 3)
        );
        Order order2 = OrderHelper.mock(
                product2,
                OrderQuantitiesHelper.mock(8, 8, 0, 4),
                "2024-10-01",
                OrderFeedBackHelper.grabMore(1)
        );
        Order order3 = OrderHelper.mock(
                product3,
                OrderQuantitiesHelper.mock(5, 0, 5, 0),
                "2024-10-01",
                OrderFeedBackHelper.empty()
        );
        Order order4 = OrderHelper.mock(
                product4,
                OrderQuantitiesHelper.mock(2, 2, 0, 1),
                "2024-10-01",
                OrderFeedBackHelper.grabMore(1)
        );
        return Orders.from(List.of(order1, order2, order3, order4));
    }
}
