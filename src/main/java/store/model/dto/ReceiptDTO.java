package store.model.dto;

public class ReceiptDTO {

    private final ReceiptProductPartDTO productPart;
    private final ReceiptAmountPartDTO amountPart;

    private ReceiptDTO(final ReceiptProductPartDTO productPart, final ReceiptAmountPartDTO amountPart) {
        this.productPart = productPart;
        this.amountPart = amountPart;
    }

    public static ReceiptDTO of(final ReceiptProductPartDTO productPart, final ReceiptAmountPartDTO amountPart) {
        return new ReceiptDTO(productPart, amountPart);
    }

    public ReceiptProductPartDTO getProductPart() {
        return productPart;
    }

    public ReceiptAmountPartDTO getAmountPart() {
        return amountPart;
    }
}
