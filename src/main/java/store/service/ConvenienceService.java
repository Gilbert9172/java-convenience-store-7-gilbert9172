package store.service;

import static store.error.ErrorMessage.PRODUCT_NOT_FOUND;
import static store.exception.OutOfStockException.outOfStock;
import static store.exception.SourceNotFoundException.notFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import store.model.dto.PreOrderDTO;
import store.model.order.Order;
import store.model.order.OrderWarning;
import store.model.order.Quantity;
import store.model.product.Product;
import store.model.product.Products;
import store.repository.ProductRepository;
import store.repository.PromotionRepository;

public class ConvenienceService {

    private final ProductRepository productRepository;
    private final PromotionRepository promotionRepository;

    public ConvenienceService(
            final ProductRepository productRepository,
            final PromotionRepository promotionRepository) {
        this.productRepository = productRepository;
        this.promotionRepository = promotionRepository;
    }

    public List<Order> generateOrders(List<PreOrderDTO> preOrderDTOS) {
        List<Order> orders = new ArrayList<>();
        for (PreOrderDTO orderDTO : preOrderDTOS) {

            String productName = orderDTO.getProductName();
            LocalDateTime orderDate = orderDTO.getOrderDate();
            int quantity = orderDTO.getQuantity();

            Order order = generateOrder(productName, orderDate, quantity);
            orders.add(order);
        }
        return orders;
    }

    private Order generateOrder(final String productName, final LocalDateTime orderDate, final int quantity) {
        validateProductStocks(productName, orderDate, quantity);

        Optional<Product> promotionProduct = productRepository.findActivePromotionProductBy(productName, orderDate);

        if (promotionProduct.isPresent()) {
            return this.promotionProductOrder(promotionProduct.get(), orderDate, quantity);
        }
        Product product = productRepository.findNormalProductBy(productName)
                .orElseThrow(() -> notFoundException(PRODUCT_NOT_FOUND));
        return this.normalProductOrder(product, orderDate, quantity);
    }

    private void validateProductStocks(final String productName, final LocalDateTime orderDate, final int quantity) {
        Products products = productRepository.findAllByName(productName, orderDate);
        if (products.outOfStock(quantity)) {
            throw outOfStock();
        }
    }

    private Order promotionProductOrder(final Product product, final LocalDateTime orderDate, final int quantity) {
        // 재고 부족 : 프로모션 수량으로 현재 주문량을 커버하지 못할 때
        if (product.outOfStock(quantity)) {
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

        // 더 가져와 : 현재 주문량에서 n개 더 가져오면 프로모션 상품 받을 수 있을 때
        if (product.hasChanceToGetPrize(quantity)) {
            int moreQuantity = product.grapMoreQuantity(quantity);
            return Order.of(
                    product,
                    Quantity.of(quantity, 0, quantity, 0),
                    orderDate,
                    OrderWarning.grapMore(moreQuantity));
        }

        // 정상 케이스
        return Order.of(
                product,
                Quantity.of(quantity, quantity, 0, product.prizeCountOf(quantity)),
                orderDate,
                OrderWarning.empty());
    }

    private Order normalProductOrder(final Product product, final LocalDateTime orderDate, final int quantity) {
        return Order.of(
                product,
                Quantity.of(quantity, 0, quantity, 0),
                orderDate,
                OrderWarning.empty());
    }
}
