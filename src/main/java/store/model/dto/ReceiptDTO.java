package store.model.dto;

import java.util.List;
import store.model.money.Money;
import store.model.order.Quantity;

public class ReceiptDTO {

    private final List<PurchasedDTO> normalProducts;
    private final List<PromotionProductDTO> promotionProducts;
    private final Money totalAmount;
    private final Quantity totalQuantity;
    private final Money promotionDiscount;
    private final Money memberShipDiscount;
    private final Money payment;

    private ReceiptDTO(final List<PurchasedDTO> normalProducts,
                       final List<PromotionProductDTO> promotionProducts,
                       final Money totalAmount,
                       final Quantity totalQuantity,
                       final Money promotionDiscount,
                       final Money memberShipDiscount,
                       final Money payment) {
        this.normalProducts = normalProducts;
        this.promotionProducts = promotionProducts;
        this.totalAmount = totalAmount;
        this.totalQuantity = totalQuantity;
        this.promotionDiscount = promotionDiscount;
        this.memberShipDiscount = memberShipDiscount;
        this.payment = payment;
    }

    public static ReceiptDTO of(final List<PurchasedDTO> normalProducts,
                                final List<PromotionProductDTO> promotionProducts,
                                final Money totalAmount,
                                final Quantity totalQuantity,
                                final Money promotionDiscount,
                                final Money memberShipDiscount,
                                final Money payment) {
        return new ReceiptDTO(
                normalProducts,
                promotionProducts,
                totalAmount,
                totalQuantity,
                promotionDiscount,
                memberShipDiscount,
                payment
        );
    }
}
