package store.io.file.write;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import store.model.product.Products;

public class MarkDownWriter implements CustomFileWriter {

    @Override
    public void write(final Products products) {
        String filePath = "src/main/resources/products.md";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("name,price,orderQuantities,promotion");
            writer.newLine();
            products.readOnlyStream()
                    .forEach(product -> {
                        try {
                            // 파일에 쓸 데이터 형식을 지정합니다.
                            writer.write(product.toString());
                            writer.newLine();
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
