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
import store.model.order.OrderWarning;
import store.model.order.Quantity;
import store.model.product.Product;
import store.model.promotion.Promotion;

public class GetMoreModifierTest {

    private final GetMoreModifier sut = new GetMoreModifier();

    @Test
    @DisplayName("프로모션 적용이 가능한 상품에 대해 고객이 해당 수량보다 적게 가져온 경우: Y를 입력했을 때")
    void getMoreModifierWithResponseYesTest() {
        // given
        Promotion promotion = PromotionHelper.mock(
                1, "2+1", 2, 1, "2024-01-01", "2024-12-31"
        );
        Product product = ProductHelper.mock("콜라", 1500, 10, promotion);
        Quantity quantity = QuantityHelper.mock(5, 3, 2, 1);
        OrderWarning orderWarning = OrderWarningHelper.grapMore(1);
        Order order = OrderHelper.mock(product, quantity, "2024-01-05", orderWarning);

        // when
        sut.modify(order, OrderModifyFlag.Y);

        // then
        Quantity actual = QuantityHelper.mock(6, 6, 0, 2);
        assertThat(quantity.equals(actual)).isTrue();
    }

    @Test
    @DisplayName("프로모션 적용이 가능한 상품에 대해 고객이 해당 수량보다 적게 가져온 경우: N을 입력했을 때")
    void getMoreModifierWithResponseNoTest() {
        // given
        Promotion promotion = PromotionHelper.mock(
                1, "2+1", 2, 1, "2024-01-01", "2024-12-31"
        );
        Product product = ProductHelper.mock("콜라", 1500, 10, promotion);
        Quantity quantity = QuantityHelper.mock(5, 3, 2, 1);
        OrderWarning orderWarning = OrderWarningHelper.grapMore(1);
        Order order = OrderHelper.mock(product, quantity, "2024-01-05", orderWarning);

        // when
        sut.modify(order, OrderModifyFlag.N);

        // then
        Quantity actual = QuantityHelper.mock(5, 3, 2, 1);
        assertThat(quantity.equals(actual)).isTrue();
    }
}
