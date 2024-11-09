package store.io.terminal;

import java.util.List;
import store.view.AmountPartView;
import store.view.ProductPartView;
import store.view.ProductView;
import store.view.PromotionProductView;
import store.view.PurchasedView;
import store.view.ReceiptView;

public class OutputTerminal {

    private final Writer writer;

    private OutputTerminal(final Writer writer) {
        this.writer = writer;
    }

    public static class TerminalHolder {
        private static final OutputTerminal INSTANCE = new OutputTerminal(Writer.initiate());
    }

    public static OutputTerminal getInstance() {
        return TerminalHolder.INSTANCE;
    }

    public void printProductsStock(List<ProductView> productViews) {

        writer.simplePrint("안녕하세요. W편의점입니다.");
        writer.printWithNewLineAfter("현재 보유하고 있는 상품입니다.");
        for (ProductView productView : productViews) {
            String template = "- %s %s원 %d개 %s";
            writer.simplePrintf(
                    template,
                    productView.getName(),
                    productView.getAmount(),
                    productView.getStock(),
                    productView.getPromotionTitle()
            );
        }
    }


    public void printReceipt(final ReceiptView view) {
        writer.simplePrint("==============W 편의점================");
        writer.simplePrintf("%-18s %-7s %-7s", "상품명", "수량", "금액");
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
            writer.simplePrint(message);
        });

        writer.simplePrint("=============증\t\t  정==============");
        List<PromotionProductView> promotionProductViews = productPartView.getPromotionProductViews();
        promotionProductViews.forEach(view -> {
            String format = FormatProperty.leftLineFormat(view.getProductName());
            String message = String.format(format + " %-8s",
                    view.getProductName(), view.getQuantity(), "");
            writer.simplePrint(message);
        });
    }

    private void printAmountPartOf(final AmountPartView amountPartView) {
        writer.simplePrint("======================================");
        String totalPurchasedAmountFormatter = FormatProperty.getFormat("총구매액", amountPartView.getTotalAmount());
        String totalPurchasedAmount = String.format(totalPurchasedAmountFormatter,
                "총구매액", amountPartView.getTotalQuantity(), amountPartView.getTotalAmount());
        writer.simplePrint(totalPurchasedAmount);

        String promotionDiscountFormatter = FormatProperty.getFormat("행사할인", amountPartView.getPromotionDiscount());
        String promotionDiscount = String.format(promotionDiscountFormatter,
                "행사할인", "", amountPartView.getPromotionDiscount());
        writer.simplePrint(promotionDiscount);

        String membershipDiscountFormatter = FormatProperty.getFormat("멤버십할인", amountPartView.getMembershipDiscount());
        String membershipDiscount = String.format(membershipDiscountFormatter,
                "멤버십할인", "", amountPartView.getMembershipDiscount());
        writer.simplePrint(membershipDiscount);

        String paymentFormatter = FormatProperty.getFormat("내실돈", amountPartView.getPayment());
        String payment = String.format(paymentFormatter,
                "내실돈", "", amountPartView.getPayment());
        writer.simplePrint(payment);
    }
}
