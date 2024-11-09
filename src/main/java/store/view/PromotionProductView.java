package store.view;

import store.model.dto.PromotionProductDTO;
import store.model.order.Quantity;

public class PromotionProductView {

    private final String productName;
    private final long quantity;

    private PromotionProductView(final String productName, final long quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    public static PromotionProductView from(PromotionProductDTO dto) {
        String productName = dto.getProductName();
        Quantity quantity = dto.getQuantity();
        return new PromotionProductView(
                productName,
                quantity.getValue()
        );
    }

    public String getProductName() {
        return productName;
    }

    public long getQuantity() {
        return quantity;
    }
}
