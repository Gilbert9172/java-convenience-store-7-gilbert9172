package store.repository;

import java.util.ArrayList;
import java.util.List;
import store.model.product.Product;

public class ProductRepository implements JpaRepository<Product> {

    private final List<Product> products = new ArrayList<>();

    @Override
    public void save(final Product product) {
        products.add(product);
    }
}
