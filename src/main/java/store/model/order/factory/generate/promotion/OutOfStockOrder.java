package store.model.order.factory.generate.promotion;

import java.time.LocalDateTime;
import store.model.order.Order;
import store.model.order.OrderFeedBack;
import store.model.order.OrderQuantities;
import store.model.order.Quantity;
import store.model.order.factory.generate.OrderGenerateHandler;
import store.model.product.Product;

public class OutOfStockOrder implements OrderGenerateHandler {

    @Override
    public Order generate(final Product product, final LocalDateTime orderDate, final Quantity quantity) {
        // 부족한 재고량
        Quantity outOfStockQuantity = product.outOfStockPromotionQuantity(quantity);
        // 프로모션에서 차감되는 재고량
        Quantity promotionQuantity = product.availablePromotionStock();
        // 상품 수
        Quantity prizeCount = product.prizeQuantityOf(promotionQuantity);
        return Order.of(
                product,
                OrderQuantities.of(quantity, promotionQuantity, quantity.minus(promotionQuantity), prizeCount),
                orderDate,
                OrderFeedBack.outOfStock(outOfStockQuantity)
        );
    }
}
