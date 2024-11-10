package store.io.file.write;

import store.model.product.Products;

public interface CustomFileWriter {
    void write(final Products products);
}
