package store.model.order.factory.modify;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.helper.model.OrderHelper;
import store.helper.model.OrderQuantitiesHelper;
import store.helper.model.OrderWarningHelper;
import store.helper.model.ProductHelper;
import store.helper.model.PromotionHelper;
import store.model.order.Order;
import store.model.order.OrderQuantities;
import store.model.order.Quantity;
import store.model.product.Product;
import store.model.promotion.Promotion;

public class OutOfStockFeedBackHandlerTest {

    private final OutOfStockFeedbackHandler sut = new OutOfStockFeedbackHandler();

    @Test
    @DisplayName("프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우: Y를 입력했을 때")
    void getMoreModifierWithYesResponseTest() {
        // given
        Promotion promotion = PromotionHelper.twoPlusOnePromotion("2024-01-01", "2024-12-31");
        Product product = ProductHelper.mock(1L, "콜라", 1500, 7, promotion);
        OrderQuantities orderQuantities = OrderQuantitiesHelper.mock(10, 6, 4, 2);
        store.model.order.OrderFeedBack orderFeedBack = OrderWarningHelper.outOfStock(Quantity.from(1),
                Quantity.from(3));
        Order order = OrderHelper.mock(product, orderQuantities, "2024-01-05", orderFeedBack);

        // when
        sut.modify(order, UserFeedBack.Y);

        // then
        OrderQuantities actual = OrderQuantitiesHelper.mock(10, 6, 4, 2);
        assertThat(orderQuantities.equals(actual)).isTrue();
    }

    @Test
    @DisplayName("프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우: N를 입력했을 때")
    void getMoreModifierWithNoResponseTest() {
        // given
        Promotion promotion = PromotionHelper.twoPlusOnePromotion("2024-01-01", "2024-12-31");
        Product product = ProductHelper.mock(1L, "콜라", 1500, 7, promotion);
        OrderQuantities orderQuantities = OrderQuantitiesHelper.mock(10, 6, 4, 2);
        store.model.order.OrderFeedBack orderFeedBack = OrderWarningHelper.outOfStock(Quantity.from(1),
                Quantity.from(3));
        Order order = OrderHelper.mock(product, orderQuantities, "2024-01-05", orderFeedBack);

        // when
        sut.modify(order, UserFeedBack.N);

        // then
        OrderQuantities actual = OrderQuantitiesHelper.mock(6, 6, 0, 2);
        assertThat(orderQuantities.equals(actual)).isTrue();
    }
}
