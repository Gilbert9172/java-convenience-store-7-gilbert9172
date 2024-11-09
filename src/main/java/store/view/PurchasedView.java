package store.view;

import store.model.dto.PurchasedDTO;
import store.model.money.Money;
import store.model.order.Quantity;

public class PurchasedView {

    private final String productName;
    private final long quantity;
    private final String totalPrice;

    private PurchasedView(final String productName, final long quantity, final String totalPrice) {
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public static PurchasedView from(final PurchasedDTO dto) {
        String productName = dto.getProductName();
        Quantity quantity = dto.getQuantity();
        Money totalPrice = dto.getTotalPrice();
        return new PurchasedView(
                productName,
                quantity.getValue(),
                totalPrice.toString()
        );
    }

    public String getProductName() {
        return productName;
    }

    public long getQuantity() {
        return quantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }
}
