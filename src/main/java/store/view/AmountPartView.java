package store.view;

import store.model.dto.ReceiptAmountPartDTO;
import store.model.money.Money;
import store.model.order.Quantity;

public class AmountPartView {

    private final String totalAmount;
    private final long totalQuantity;
    private final String promotionDiscount;
    private final String membershipDiscount;
    private final String payment;

    private AmountPartView(final String totalAmount, final long totalQuantity, final String promotionDiscount,
                           final String membershipDiscount,
                           final String payment) {
        this.totalAmount = totalAmount;
        this.totalQuantity = totalQuantity;
        this.promotionDiscount = promotionDiscount;
        this.membershipDiscount = membershipDiscount;
        this.payment = payment;
    }

    public static AmountPartView from(ReceiptAmountPartDTO dto) {
        Money totalAmount = dto.getTotalAmount();
        Quantity totalQuantity = dto.getTotalQuantity();
        Money promotionDiscount = dto.getPromotionDiscount();
        Money membershipDiscount = dto.getMembershipDiscount();
        Money payment = dto.getPayment();
        return new AmountPartView(
                totalAmount.toString(),
                totalQuantity.getValue(),
                promotionDiscount.toString(),
                membershipDiscount.toString(),
                payment.toString()
        );
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public long getTotalQuantity() {
        return totalQuantity;
    }

    public String getPromotionDiscount() {
        return String.format("-%s", promotionDiscount);
    }

    public String getMembershipDiscount() {
        return String.format("-%s", membershipDiscount);
    }

    public String getPayment() {
        return payment;
    }
}
