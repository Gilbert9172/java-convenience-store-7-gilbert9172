package store.service;

import store.exception.SourceNotFoundException;
import store.model.order.Quantity;
import store.model.product.Product;
import store.repository.ProductRepository;

public class StockManageService {

    private final ProductRepository productRepository;

    public StockManageService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void updateStock(final Product product, final Quantity orderQuantity) {
        if (product.canHandle(orderQuantity)) {
            product.decreasedStock(orderQuantity);
            return;
        }
        Quantity remainingStock = product.remainingStock(orderQuantity);
        product.flushAllStock();

        Product normalProduct = productRepository.findNormalProductBy(product.getName())
                .orElseThrow(SourceNotFoundException::productNotFoundException);
        normalProduct.decreasedStock(remainingStock);
    }
}
