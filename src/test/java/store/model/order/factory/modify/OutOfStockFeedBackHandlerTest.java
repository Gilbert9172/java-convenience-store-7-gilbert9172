package store.model.order.factory.modify;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.helper.OrderHelper;
import store.helper.OrderWarningHelper;
import store.helper.ProductHelper;
import store.helper.PromotionHelper;
import store.helper.QuantityHelper;
import store.model.order.Order;
import store.model.order.OrderFeedBack;
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
        Promotion promotion = PromotionHelper.mock(
                1, "2+1", 2, 1, "2024-01-01", "2024-12-31"
        );
        Product product = ProductHelper.mock("콜라", 1500, 7, promotion);
        OrderQuantities orderQuantities = QuantityHelper.mock(10, 6, 4, 2);
        OrderFeedBack orderFeedBack = OrderWarningHelper.outOfStock(Quantity.of(4));
        Order order = OrderHelper.mock(product, orderQuantities, "2024-01-05", orderFeedBack);

        // when
        sut.modify(order, UserFeedBack.Y);

        // then
        OrderQuantities actual = QuantityHelper.mock(10, 6, 4, 2);
        assertThat(orderQuantities.equals(actual)).isTrue();
    }

    @Test
    @DisplayName("프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우: Y를 입력했을 때")
    void getMoreModifierWithNoResponseTest() {
        // given
        Promotion promotion = PromotionHelper.mock(
                1, "2+1", 2, 1, "2024-01-01", "2024-12-31"
        );
        Product product = ProductHelper.mock("콜라", 1500, 7, promotion);
        OrderQuantities orderQuantities = QuantityHelper.mock(10, 6, 4, 2);
        OrderFeedBack orderFeedBack = OrderWarningHelper.outOfStock(Quantity.of(4));
        Order order = OrderHelper.mock(product, orderQuantities, "2024-01-05", orderFeedBack);

        // when
        sut.modify(order, UserFeedBack.N);

        // then
        OrderQuantities actual = QuantityHelper.mock(6, 6, 0, 2);
        assertThat(orderQuantities.equals(actual)).isTrue();
    }
}
