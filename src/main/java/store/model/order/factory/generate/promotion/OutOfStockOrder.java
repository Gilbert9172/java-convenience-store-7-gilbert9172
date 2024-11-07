package store.model.order.factory.generate.promotion;

import java.time.LocalDateTime;
import store.model.order.Order;
import store.model.order.OrderWarning;
import store.model.order.Quantity;
import store.model.order.factory.generate.OrderGenerator;
import store.model.product.Product;

public class OutOfStockOrder implements OrderGenerator {

    @Override
    public Order generate(final Product product, final LocalDateTime orderDate, final int quantity) {
        // 부족한 재고량
        int outOfStockQuantity = product.outOfStockQuantity(quantity);
        // 프로모션에서 차감되는 재고량
        int promotionQuantity = product.promotionQuantityOf(quantity);
        // 상품 수
        int prizeCount = product.prizeCountOf(promotionQuantity);
        return Order.of(
                product,
                Quantity.of(quantity, promotionQuantity, 0, prizeCount),
                orderDate,
                OrderWarning.outOfStock(outOfStockQuantity)
        );
    }
}
