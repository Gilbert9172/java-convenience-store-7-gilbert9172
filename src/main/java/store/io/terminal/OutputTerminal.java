package store.io.terminal;

import java.util.List;
import store.view.AmountPartView;
import store.view.ProductPartView;
import store.view.ProductView;
import store.view.PromotionProductView;
import store.view.PurchasedView;
import store.view.ReceiptView;

public class OutputTerminal {

    public static class TerminalHolder {
        private static final OutputTerminal INSTANCE = new OutputTerminal();
    }

    public static OutputTerminal getInstance() {
        return TerminalHolder.INSTANCE;
    }

    public void printProductsStock(List<ProductView> productViews) {

        Writer.printWithNewLineBefore("안녕하세요. W편의점입니다.");
        Writer.printWithNewLineAfter("현재 보유하고 있는 상품입니다.");
        for (ProductView productView : productViews) {
            String template = "- %s %s원 %s %s";
            Writer.simplePrintf(
                    template,
                    productView.getName(),
                    productView.getAmount(),
                    productView.getStock(),
                    productView.getPromotionTitle()
            );
        }
    }


    public void printReceipt(final ReceiptView view) {
        Writer.simplePrint("==============W 편의점================");
        Writer.simplePrintf("%-18s %-7s %-7s", "상품명", "수량", "금액");
        ProductPartView productPart = view.getProductPart();
        printProductPartOf(productPart);

        AmountPartView amountPart = view.getAmountPart();
        printAmountPartOf(amountPart);
    }

    private void printProductPartOf(final ProductPartView productPartView) {
        List<PurchasedView> purchasedViews = productPartView.getPurchasedViews();
        purchasedViews.forEach(view -> {
            String format = FormatProperty.getFormat(view.getProductName(), view.getTotalPrice());
            String message = String.format(format, view.getProductName(), view.getQuantity(), view.getTotalPrice());
            Writer.simplePrint(message);
        });

        Writer.simplePrint("=============증       정==============");
        List<PromotionProductView> promotionProductViews = productPartView.getPromotionProductViews();
        promotionProductViews.forEach(view -> {
            String format = FormatProperty.leftLineFormat(view.getProductName());
            String message = String.format(format + " %-8s",
                    view.getProductName(), view.getQuantity(), "");
            Writer.simplePrint(message);
        });
    }

    private void printAmountPartOf(final AmountPartView amountPartView) {
        Writer.simplePrint("======================================");
        String totalPurchasedAmountFormatter = FormatProperty.getFormat("총구매액", amountPartView.getTotalAmount());
        String totalPurchasedAmount = String.format(totalPurchasedAmountFormatter,
                "총구매액", amountPartView.getTotalQuantity(), amountPartView.getTotalAmount());
        Writer.simplePrint(totalPurchasedAmount);

        String promotionDiscountFormatter = FormatProperty.getFormat("행사할인", amountPartView.getPromotionDiscount());
        String promotionDiscount = String.format(promotionDiscountFormatter,
                "행사할인", "", amountPartView.getPromotionDiscount());
        Writer.simplePrint(promotionDiscount);

        String membershipDiscountFormatter = FormatProperty.getFormat("멤버십할인", amountPartView.getMembershipDiscount());
        String membershipDiscount = String.format(membershipDiscountFormatter,
                "멤버십할인", "", amountPartView.getMembershipDiscount());
        Writer.simplePrint(membershipDiscount);

        String paymentFormatter = FormatProperty.paymentFormat("내실돈", amountPartView.getPayment());
        String payment = String.format(paymentFormatter,
                "내실돈", "", amountPartView.getPayment());
        Writer.simplePrint(payment);
    }
}
