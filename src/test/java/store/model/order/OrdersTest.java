package store.model.order;

import static org.assertj.core.api.Assertions.assertThat;
import static store.helper.extension.DtoTestHelper.assertPromotionProductDTO;
import static store.helper.extension.DtoTestHelper.assertPurchasedDTO;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.helper.model.OrdersHelper;
import store.model.dto.PromotionProductDTO;
import store.model.dto.PurchasedDTO;
import store.model.money.Money;

public class OrdersTest {

    @Test
    @DisplayName("구매 목록 : 상품명, 수량, 금액")
    void mapToPurchasedProducts() {
        // given
        Orders orders = OrdersHelper.testSet1();

        // when
        List<PurchasedDTO> dtos = orders.mapToPurchasedProducts();

        // then
        assertThat(dtos.size()).isEqualTo(4);

        assertPurchasedDTO(
                dtos.get(0),
                "콜라", Quantity.from(10), Money.from(10000)
        );

        assertPurchasedDTO(
                dtos.get(1),
                "오렌지주스", Quantity.from(8), Money.from(14400)
        );

        assertPurchasedDTO(
                dtos.get(2),
                "물", Quantity.from(5), Money.from(2500)
        );

        assertPurchasedDTO(
                dtos.get(3),
                "감자칩", Quantity.from(2), Money.from(3000)
        );
    }

    @Test
    @DisplayName("프로모션 혜택 : 상품명 , 수량")
    void mapToPromotionPrizes() {
        // given
        Orders orders = OrdersHelper.testSet1();

        // when
        List<PromotionProductDTO> dtos = orders.mapToPromotionPrizes();

        // then
        assertThat(dtos.size()).isEqualTo(3);

        assertPromotionProductDTO(
                dtos.get(0),
                "콜라", Quantity.from(2)
        );

        assertPromotionProductDTO(
                dtos.get(1),
                "오렌지주스", Quantity.from(4)
        );

        assertPromotionProductDTO(
                dtos.get(2),
                "감자칩", Quantity.from(1)
        );
    }

    @Test
    @DisplayName("할인 전 총 가격")
    void totalOriginalAmount() {
        // given
        Orders orders = OrdersHelper.testSet1();

        // when
        Money actual = orders.totalOriginalAmount();

        // then
        Money expected = Money.from(29900);
        assertThat(actual.equals(expected)).isTrue();
    }

    @Test
    @DisplayName("총 구매 수량")
    void totalPurchasedQuantity() {
        // given
        Orders orders = OrdersHelper.testSet1();

        // when
        Quantity actual = orders.totalPurchasedQuantity();

        // then
        Quantity expected = Quantity.from(25);
        assertThat(actual.equals(expected)).isTrue();
    }

    @Test
    @DisplayName("구매한 상품 중, 프로모션 적용된 상품의 총 금액")
    void totalPromotionProductsAmount() {
        // given
        Orders orders = OrdersHelper.testSet1();

        // when
        Money actual = orders.totalPromotionProductsAmount();

        // then
        Money expected = Money.from(10700);
        assertThat(actual.equals(expected)).isTrue();
    }

    @Test
    @DisplayName("구매한 상품 중, 프로모션 적용되지 않은 상품의 총 금액")
    void totalNormalProductsAmount() {
        // given
        Orders orders = OrdersHelper.testSet1();

        // when
        Money actual = orders.totalNormalProductsAmount();

        // then
        Money expected = Money.from(2500);
        assertThat(actual.equals(expected)).isTrue();
    }
}
