package store.service;

import static org.assertj.core.api.Assertions.assertThat;
import static store.model.order.Quantity.ONE;
import static store.model.order.Quantity.ZERO;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.helper.OrderHelper;
import store.helper.OrderQuantitiesHelper;
import store.helper.OrderWarningHelper;
import store.helper.ProductHelper;
import store.helper.PromotionHelper;
import store.model.order.Order;
import store.model.order.OrderFeedBack;
import store.model.order.OrderQuantities;
import store.model.order.Orders;
import store.model.order.Quantity;
import store.model.product.Product;
import store.model.promotion.Promotion;
import store.repository.ProductRepository;

public class StockManageServiceTest {

    private final ProductRepository productRepository = new ProductRepository();
    private final StockManageService sut = new StockManageService(productRepository);

    @Test
    @DisplayName("[2+1 행사] 재고 확인 - 프로모션 재고 보다 많이 구매")
    void updateProductStocksCase1() {
        // given
        Promotion promotion = PromotionHelper.twoPlusOnePromotion("2024-01-01", "2024-12-31");
        Product promotionProduct = ProductHelper.mock("콜라", 1500, 5, promotion);
        OrderQuantities quantity = OrderQuantitiesHelper.mock(10, 6, 4, 2);
        OrderFeedBack orderFeedBack = OrderWarningHelper.grabMore(ONE);
        Order order = OrderHelper.mock(promotionProduct, quantity, "2024-01-05", orderFeedBack);
        Orders orders = Orders.of(List.of(order));

        Product normalProduct = ProductHelper.mock("콜라", 1500, 10, null);
        productRepository.save(normalProduct);
        productRepository.save(promotionProduct);

        // when
        sut.updateProductStocks(orders);

        // then
        Quantity currentPromotionStock = promotionProduct.currentStock();
        Quantity currentNormalStock = normalProduct.currentStock();
        assertThat(currentPromotionStock.equals(ZERO)).isTrue();
        assertThat(currentNormalStock.equals(Quantity.of(5))).isTrue();
    }

    @Test
    @DisplayName("[2+1 행사] 재고 확인 - 프로모션 재고만큼 구매")
    void updateProductStocksCase2() {
        // given
        Promotion promotion = PromotionHelper.twoPlusOnePromotion("2024-01-01", "2024-12-31");
        Product promotionProduct = ProductHelper.mock("콜라", 1500, 6, promotion);
        OrderQuantities quantity = OrderQuantitiesHelper.mock(6, 6, 0, 2);
        OrderFeedBack orderFeedBack = OrderWarningHelper.grabMore(ONE);
        Order order = OrderHelper.mock(promotionProduct, quantity, "2024-01-05", orderFeedBack);
        Orders orders = Orders.of(List.of(order));
        productRepository.save(promotionProduct);

        // when
        sut.updateProductStocks(orders);

        // then
        Quantity currentPromotionStock = promotionProduct.currentStock();
        assertThat(currentPromotionStock.equals(ZERO)).isTrue();
    }
}
