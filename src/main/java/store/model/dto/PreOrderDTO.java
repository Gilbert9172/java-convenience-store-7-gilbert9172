package store.model.dto;

import java.time.LocalDateTime;

public class PreOrderDTO {

    private final String productName;
    private final int quantity;
    private final LocalDateTime orderDate;

    private PreOrderDTO(
            final String productName,
            final int quantity,
            final LocalDateTime orderDate) {
        this.productName = productName;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }

    public static PreOrderDTO of(
            final String productName,
            final int quantity,
            final LocalDateTime orderDate) {
        return new PreOrderDTO(productName, quantity, orderDate);
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }
}
