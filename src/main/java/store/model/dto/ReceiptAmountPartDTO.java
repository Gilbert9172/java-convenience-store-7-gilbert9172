package store.model.dto;

import store.model.money.Money;
import store.model.order.Quantity;

public class ReceiptAmountPartDTO {

    private final Money totalAmount;
    private final Quantity totalQuantity;
    private final Money promotionDiscount;
    private final Money membershipDiscount;
    private final Money payment;

    private ReceiptAmountPartDTO(final Money totalAmount, final Quantity totalQuantity, final Money promotionDiscount,
                                 final Money membershipDiscount, final Money payment) {
        this.totalAmount = totalAmount;
        this.totalQuantity = totalQuantity;
        this.promotionDiscount = promotionDiscount;
        this.membershipDiscount = membershipDiscount;
        this.payment = payment;
    }

    public static ReceiptAmountPartDTO of(final Money totalAmount,
                                          final Quantity totalQuantity,
                                          final Money promotionDiscount,
                                          final Money membershipDiscount,
                                          final Money payment) {
        return new ReceiptAmountPartDTO(totalAmount, totalQuantity, promotionDiscount, membershipDiscount, payment);
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

    public Money getMembershipDiscount() {
        return membershipDiscount;
    }

    public Money getPayment() {
        return payment;
    }
}
