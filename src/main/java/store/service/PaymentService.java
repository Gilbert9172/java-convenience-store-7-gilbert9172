package store.service;

import static store.model.discount.DiscountType.MEMBERSHIP;
import static store.model.discount.DiscountType.PROMOTION;

import java.util.List;
import store.model.discount.DiscountPolicyFactory;
import store.model.dto.PromotionProductDTO;
import store.model.dto.PurchasedDTO;
import store.model.dto.ReceiptAmountPartDTO;
import store.model.dto.ReceiptDTO;
import store.model.dto.ReceiptProductPartDTO;
import store.model.money.Money;
import store.model.order.Orders;
import store.model.order.Quantity;
import store.model.order.factory.modify.UserFeedBack;

public class PaymentService {

    private final DiscountPolicyFactory discountPolicyFactory;

    public PaymentService(final DiscountPolicyFactory discountPolicyFactory) {
        this.discountPolicyFactory = discountPolicyFactory;
    }

    public ReceiptDTO offerReceipt(final Orders orders,
                                   final UserFeedBack feedBack) {
        ReceiptProductPartDTO receiptProductPart = summaryPurchasedProductsOf(orders);
        ReceiptAmountPartDTO receiptAmountPart = summaryAmountOf(orders, feedBack);
        return ReceiptDTO.of(receiptProductPart, receiptAmountPart);
    }

    private ReceiptProductPartDTO summaryPurchasedProductsOf(final Orders orders) {
        List<PurchasedDTO> purchasedProducts = orders.mapToPurchasedProducts();
        List<PromotionProductDTO> promotionPrizes = orders.mapToPromotionPrizes();
        return ReceiptProductPartDTO.of(purchasedProducts, promotionPrizes);
    }

    private ReceiptAmountPartDTO summaryAmountOf(final Orders orders,
                                                 final UserFeedBack feedBack) {
        Money totalOriginalAmount = orders.totalOriginalAmount();
        Quantity totalPurchasedQuantity = orders.totalPurchasedQuantity();
        Money promotionDiscount = discountPolicyFactory.applyDiscountByType(PROMOTION, orders);
        Money membershipDiscount = membershipDiscount(orders, feedBack);
        Money paymentAmount = calculateFinalPaymentOf(totalOriginalAmount, promotionDiscount, membershipDiscount);
        return ReceiptAmountPartDTO.of(
                totalOriginalAmount, totalPurchasedQuantity, promotionDiscount, membershipDiscount, paymentAmount
        );
    }

    private Money calculateFinalPaymentOf(final Money totalOriginalPrice,
                                          final Money promotionDiscount,
                                          final Money membershipDiscount) {
        Money totalDiscountAmount = promotionDiscount.add(membershipDiscount);
        return totalOriginalPrice.minus(totalDiscountAmount);
    }

    private Money membershipDiscount(final Orders orders, final UserFeedBack feedBack) {
        if (feedBack.responseYes()) {
            return discountPolicyFactory.applyDiscountByType(MEMBERSHIP, orders);
        }
        return Money.ZERO;
    }
}
