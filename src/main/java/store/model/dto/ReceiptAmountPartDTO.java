package store.model.dto;

import store.model.money.Money;
import store.model.order.Quantity;

public class ReceiptAmountPartDTO {

    private final Money totalAmount;
    private final Quantity totalQuantity;
    private final Money promotionDiscount;
    private final Money memberShipDiscount;
    private final Money payment;

    private ReceiptAmountPartDTO(final Money totalAmount, final Quantity totalQuantity, final Money promotionDiscount,
                                 final Money memberShipDiscount, final Money payment) {
        this.totalAmount = totalAmount;
        this.totalQuantity = totalQuantity;
        this.promotionDiscount = promotionDiscount;
        this.memberShipDiscount = memberShipDiscount;
        this.payment = payment;
    }

    public static ReceiptAmountPartDTO of(final Money totalAmount,
                                          final Quantity totalQuantity,
                                          final Money promotionDiscount,
                                          final Money memberShipDiscount,
                                          final Money payment) {
        return new ReceiptAmountPartDTO(totalAmount, totalQuantity, promotionDiscount, memberShipDiscount, payment);
    }

    public Money getTotalAmount() {
        return totalAmount;
    }

    public Quantity getTotalQuantity() {
        return totalQuantity;
    }

    public Money getPromotionDiscount() {
        return promotionDiscount;
    }

    public Money getMemberShipDiscount() {
        return memberShipDiscount;
    }

    public Money getPayment() {
        return payment;
    }
}
