package store.service;

import store.exception.SourceNotFoundException;
import store.model.order.Order;
import store.model.order.Orders;
import store.model.order.Quantity;
import store.model.product.Product;
import store.repository.ProductRepository;

public class StockManageService {

    private final ProductRepository productRepository;

    public StockManageService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void updateStocks(Orders orders) {
        for (Order order : orders.getOrders()) {
            Product product = order.getProduct();
            Quantity orderQuantity = order.getOrdrerQuantity();
            Quantity currentStock = product.currentStock();

            if (currentStock.boeThan(orderQuantity)) {
                product.decreasedStock(orderQuantity);
            }
            if (currentStock.LowerThan(orderQuantity)) {
                Quantity remainingStock = orderQuantity.minus(currentStock);
                product.decreasedStock(currentStock);

                Product normalProduct = productRepository.findNormalProductBy(product.getName())
                        .orElseThrow(SourceNotFoundException::productNotFoundException);
                normalProduct.decreasedStock(remainingStock);
            }
        }
    }
}
