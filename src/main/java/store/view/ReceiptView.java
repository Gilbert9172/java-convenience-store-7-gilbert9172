package store.view;

import store.model.dto.ReceiptAmountPartDTO;
import store.model.dto.ReceiptDTO;
import store.model.dto.ReceiptProductPartDTO;

public class ReceiptView {

    private final ProductPartView productPart;
    private final AmountPartView amountPart;

    private ReceiptView(final ProductPartView productPart, final AmountPartView amountPart) {
        this.productPart = productPart;
        this.amountPart = amountPart;
    }

    public static ReceiptView from(final ReceiptDTO receiptDTO) {
        ReceiptProductPartDTO productPartDTO = receiptDTO.getProductPart();
        ReceiptAmountPartDTO amountPartDTO = receiptDTO.getAmountPart();
        return new ReceiptView(
                ProductPartView.from(productPartDTO),
                AmountPartView.from(amountPartDTO)
        );
    }

    public ProductPartView getProductPart() {
        return productPart;
    }

    public AmountPartView getAmountPart() {
        return amountPart;
    }
}
