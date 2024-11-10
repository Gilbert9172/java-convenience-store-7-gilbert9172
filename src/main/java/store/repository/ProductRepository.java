package store.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import store.model.product.Product;
import store.model.product.Products;

public class ProductRepository implements JpaRepository<Product> {

    private final List<Product> products = new ArrayList<>();

    @Override
    public void save(final Product product) {
        products.add(product);
    }

    @Override
    public void clear() {
        products.clear();
    }

    public Products findAll() {
        return Products.from(products);
    }

    public Products findAllByName(final String name, final LocalDateTime now) {
        List<Product> queriedProducts = products.stream()
                .filter(product -> product.hasSameName(name))
                .filter(product -> product.isAvailable(now))
                .toList();
        return Products.from(queriedProducts);
    }

    public Optional<Product> findActivePromotionProductBy(final String name, final LocalDateTime now) {
        return products.stream()
                .filter(product -> product.hasSameName(name))
                .filter(Product::inStock)
                .filter(Product::promotionApplied)
                .filter(product -> product.isAvailable(now))
                .findFirst();
    }

    public Optional<Product> findNormalProductBy(final String name) {
        return products.stream()
                .filter(product -> product.hasSameName(name))
                .filter(Product::promotionNotApplied)
                .findFirst();
    }
}
