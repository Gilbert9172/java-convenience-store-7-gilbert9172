package store.service;

import static store.exception.OutOfStockException.outOfStock;
import static store.exception.SourceNotFoundException.productNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import store.exception.SourceNotFoundException;
import store.model.dto.PreOrderDTO;
import store.model.order.Order;
import store.model.order.Orders;
import store.model.order.Quantity;
import store.model.order.factory.generate.NormalOrderFactory;
import store.model.order.factory.generate.PromotionOrderFactory;
import store.model.order.factory.modify.OrderFeedBackHandlerFactory;
import store.model.order.factory.modify.UserFeedBack;
import store.model.product.Product;
import store.model.product.Products;
import store.repository.ProductRepository;

public class OrderService {

    private final ProductRepository productRepository;
    private final PromotionOrderFactory promotionOrderFactory;
    private final NormalOrderFactory normalOrderFactory;
    private final OrderFeedBackHandlerFactory orderFeedBackHandlerFactory;

    public OrderService(final ProductRepository productRepository,
                        final PromotionOrderFactory promotionOrderFactory,
                        final NormalOrderFactory normalOrderFactory,
                        final OrderFeedBackHandlerFactory orderFeedBackHandlerFactory) {
        this.productRepository = productRepository;
        this.promotionOrderFactory = promotionOrderFactory;
        this.normalOrderFactory = normalOrderFactory;
        this.orderFeedBackHandlerFactory = orderFeedBackHandlerFactory;
    }

    public Orders generateOrders(final List<PreOrderDTO> preOrderDTOS) {
        List<Order> orders = preOrderDTOS.stream()
                .map(dto -> {
                    String productName = dto.getProductName();
                    LocalDateTime orderDate = dto.getOrderDate();
                    Quantity quantity = Quantity.from(dto.getQuantity());
                    return generateOrder(productName, orderDate, quantity);
                })
                .toList();
        return Orders.of(orders);
    }

    private Order generateOrder(final String productName,
                                final LocalDateTime orderDate,
                                final Quantity quantity) {
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
                                       final Quantity quantity) {
        Products products = productRepository.findAllByName(productName, orderDate);
        if (products.isEmpty()) {
            throw productNotFoundException();
        }

        if (products.outOfStock(quantity)) {
            throw outOfStock();
        }
    }

    public void updateOrder(Order order, UserFeedBack flag) {
        orderFeedBackHandlerFactory.updateOrderByCondition(order, flag);
    }
}
