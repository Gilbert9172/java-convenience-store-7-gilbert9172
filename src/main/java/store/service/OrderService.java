package store.service;

import static store.exception.OutOfStockException.outOfStock;
import static store.exception.SourceNotFoundException.productNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import store.exception.SourceNotFoundException;
import store.model.dto.PreOrderDTO;
import store.model.order.Order;
import store.model.order.factory.generate.NormalOrderFactory;
import store.model.order.factory.generate.PromotionOrderFactory;
import store.model.order.factory.modify.OrderModifierFactory;
import store.model.order.factory.modify.OrderModifyFlag;
import store.model.product.Product;
import store.model.product.Products;
import store.repository.ProductRepository;

public class OrderService {

    private final ProductRepository productRepository;
    private final PromotionOrderFactory promotionOrderFactory;
    private final NormalOrderFactory normalOrderFactory;
    private final OrderModifierFactory orderModifierFactory;

    public OrderService(final ProductRepository productRepository,
                        final PromotionOrderFactory promotionOrderFactory,
                        final NormalOrderFactory normalOrderFactory,
                        final OrderModifierFactory orderModifierFactory) {
        this.productRepository = productRepository;
        this.promotionOrderFactory = promotionOrderFactory;
        this.normalOrderFactory = normalOrderFactory;
        this.orderModifierFactory = orderModifierFactory;
    }

    public List<Order> generateOrders(final List<PreOrderDTO> preOrderDTOS) {
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

    private Order generateOrder(final String productName,
                                final LocalDateTime orderDate,
                                final int quantity) {
        validateProductStocks(productName, orderDate, quantity);

        Optional<Product> promotionProduct = productRepository.findActivePromotionProductBy(productName, orderDate);

        if (promotionProduct.isPresent()) {
            return promotionOrderFactory.generateOrderByCondition(promotionProduct.get(), orderDate, quantity);
        }

        Product product = productRepository.findNormalProductBy(productName)
                .orElseThrow(SourceNotFoundException::productNotFoundException);
        return normalOrderFactory.generateOrderByCondition(product, orderDate, quantity);
    }

    private void validateProductStocks(final String productName,
                                       final LocalDateTime orderDate,
                                       final int quantity) {
        Products products = productRepository.findAllByName(productName, orderDate);
        if (products.isEmpty()) {
            throw productNotFoundException();
        }

        if (products.outOfStock(quantity)) {
            throw outOfStock();
        }
    }

    public void updateOrder(Order order, OrderModifyFlag flag) {
        orderModifierFactory.updateOrderByCondition(order, flag);
    }
}
