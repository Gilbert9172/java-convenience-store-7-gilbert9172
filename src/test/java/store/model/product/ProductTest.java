package store.model.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.exception.OutOfStockException;
import store.helper.ProductHelper;
import store.model.order.Quantity;

public class ProductTest {

    @Test
    @DisplayName("재고 감소")
    void decreaseStockTest() {
        // given
        Product product = ProductHelper.mock("콜라", 1500, 10, null);

        // when
        Quantity decreaseQuantity = Quantity.of(5);
        product.decreasedStock(decreaseQuantity);

        // then
        Quantity expected = Quantity.of(5);
        Quantity currentStock = product.currentStock();
        assertThat(currentStock.equals(expected)).isTrue();
    }

    @Test
    @DisplayName("재고 감소 시 현재 재고량이 0보다 작은 경우")
    void decreaseStockTestCase2() {
        // given
        Product product = ProductHelper.mock("콜라", 1500, 10, null);

        // when & then
        Quantity decreaseQuantity = Quantity.of(15);
        assertThrows(
                OutOfStockException.class,
                () -> product.decreasedStock(decreaseQuantity),
                "[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요."
        );
    }
}
