package store.model.dto;

import store.model.order.Quantity;

public class PromotionProductDTO {

    private final String productName;
    private final Quantity quantity;

    private PromotionProductDTO(final String productName, final Quantity quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    public static PromotionProductDTO of(final String productName, final Quantity quantity) {
        return new PromotionProductDTO(productName, quantity);
    }

    public String getProductName() {
        return productName;
    }

    public Quantity getQuantity() {
        return quantity;
    }
}
