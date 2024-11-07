package store.model.dto;

import static java.util.Objects.isNull;
import static store.exception.InvalidQuantityException.invalidQuantity;
import static store.exception.ShouldNotBeNullException.nullArg;

import java.time.LocalDateTime;

public class PreOrderDTO {

    private final String productName;
    private final int quantity;
    private final LocalDateTime orderDate;

    private PreOrderDTO(final String productName,
                        final int quantity,
                        final LocalDateTime orderDate) {
        this.productName = productName;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }

    public static PreOrderDTO of(final String productName,
                                 final int quantity,
                                 final LocalDateTime orderDate) {
        facadeValidate(productName, quantity, orderDate);
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

    private static void facadeValidate(final String productName,
                                       final int quantity,
                                       final LocalDateTime orderDate) {
        if (isNull(productName) || productName.isBlank() || productName.isEmpty()) {
            throw nullArg();
        }

        if (isNull(orderDate)) {
            throw nullArg();
        }

        if (quantity <= 0) {
            throw invalidQuantity();
        }
    }
}
