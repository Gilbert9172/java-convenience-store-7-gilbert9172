package store.model.product;

import static store.model.order.Quantity.ZERO;

import java.util.List;
import java.util.stream.Stream;
import store.model.order.Quantity;
import store.view.ProductView;

public class Products {

    private final List<Product> products;

    private Products(final List<Product> products) {
        this.products = products;
    }

    public static Products from(final List<Product> products) {
        return new Products(products);
    }

    public Stream<Product> readOnlyStream() {
        return products.stream();
    }

    public boolean outOfStock(Quantity orderQuantity) {
        Quantity allStocks = allStocks();
        return allStocks.LowerThan(orderQuantity) || allStocks.equals(ZERO);
    }

    private Quantity allStocks() {
        List<Quantity> stocks = products.stream()
                .map(Product::currentStock)
                .toList();
        return Quantity.addAll(stocks);
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public List<ProductView> mapToView() {
        return products.stream()
                .map(ProductView::from)
                .toList();
    }
}
