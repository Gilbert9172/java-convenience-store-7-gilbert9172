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

public class GetMoreFeedBackHandlerTest {

    private final GetMoreFeedbackHandler sut = new GetMoreFeedbackHandler();

    @Test
    @DisplayName("프로모션 적용이 가능한 상품에 대해 고객이 해당 수량보다 적게 가져온 경우: Y를 입력했을 때")
    void getMoreModifierWithResponseYesTest() {
        // given
        Promotion promotion = PromotionHelper.twoPlusOnePromotion("2024-01-01", "2024-12-31");
        Product product = ProductHelper.mock(1L, "콜라", 1500, 10, promotion);
        OrderQuantities orderQuantities = OrderQuantitiesHelper.mock(5, 3, 2, 1);
        store.model.order.OrderFeedBack orderFeedBack = OrderWarningHelper.grabMore(Quantity.from(1));
        Order order = OrderHelper.mock(product, orderQuantities, "2024-01-05", orderFeedBack);

        // when
        sut.modify(order, UserFeedBack.Y);

        // then
        OrderQuantities actual = OrderQuantitiesHelper.mock(6, 6, 0, 2);
        assertThat(orderQuantities.equals(actual)).isTrue();
    }

    @Test
    @DisplayName("프로모션 적용이 가능한 상품에 대해 고객이 해당 수량보다 적게 가져온 경우: N을 입력했을 때")
    void getMoreModifierWithResponseNoTest() {
        // given
        Promotion promotion = PromotionHelper.twoPlusOnePromotion("2024-01-01", "2024-12-31");
        Product product = ProductHelper.mock(1L, "콜라", 1500, 10, promotion);
        OrderQuantities orderQuantities = OrderQuantitiesHelper.mock(5, 3, 2, 1);
        store.model.order.OrderFeedBack orderFeedBack = OrderWarningHelper.grabMore(Quantity.from(1));
        Order order = OrderHelper.mock(product, orderQuantities, "2024-01-05", orderFeedBack);

        // when
        sut.modify(order, UserFeedBack.N);

        // then
        OrderQuantities actual = OrderQuantitiesHelper.mock(5, 3, 2, 1);
        assertThat(orderQuantities.equals(actual)).isTrue();
    }
}
