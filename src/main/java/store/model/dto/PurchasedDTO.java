package store.model.dto;

import store.model.money.Money;
import store.model.order.Quantity;

public class PurchasedDTO {

    private final String productName;
    private final Quantity quantity;
    private final Money totalPrice;

    private PurchasedDTO(final String productName, final Quantity quantity, final Money totalPrice) {
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public static PurchasedDTO of(final String productName, final Quantity quantity, final Money totalPrice) {
        return new PurchasedDTO(productName, quantity, totalPrice);
    }

    public String getProductName() {
        return productName;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Money getTotalPrice() {
        return totalPrice;
    }
}
