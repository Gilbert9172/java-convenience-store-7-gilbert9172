package store.model.product;

import java.util.List;

public class Products {

    private final List<Product> products;

    private Products(final List<Product> products) {
        this.products = products;
    }

    public static Products from(final List<Product> products) {
        return new Products(products);
    }

    public boolean outOfStock(int orderQuantity) {
        return allStocks() < orderQuantity;
    }

    private int allStocks() {
        return products.stream()
                .mapToInt(Product::getQuantity)
                .sum();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }
}
