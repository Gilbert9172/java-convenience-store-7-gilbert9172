package store.io.terminal;

import static store.io.terminal.ReceiptLineFormatter.FormatType.AMOUNT;
import static store.io.terminal.ReceiptLineFormatter.FormatType.PAYMENT;
import static store.io.terminal.ReceiptLineFormatter.formattedAmount;
import static store.io.terminal.ReceiptLineFormatter.formattedTotalAmount;

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
            String template = "- %s %s원 %s개 %s";
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
        Writer.simplePrint(
                ReceiptLineFormatter.fixedFormat("상품명", "수량", "금액")
        );

        ProductPartView productPart = view.getProductPart();
        printProductPartOf(productPart);

        AmountPartView amountPart = view.getAmountPart();
        printAmountPartOf(amountPart);
    }

    private void printProductPartOf(final ProductPartView productPartView) {
        List<PurchasedView> purchasedViews = productPartView.getPurchasedViews();
        purchasedViews.forEach(view ->
                Writer.simplePrint(
                        formattedTotalAmount(view.getProductName(), view.getQuantity(), view.getTotalPrice(), AMOUNT)
                ));

        Writer.simplePrint("=============증       정==============");

        List<PromotionProductView> promotionProductViews = productPartView.getPromotionProductViews();
        promotionProductViews.forEach(view ->
                Writer.simplePrint(
                        formattedTotalAmount(view.getProductName(), view.getQuantity(), "", AMOUNT)
                )
        );
    }

    private void printAmountPartOf(final AmountPartView amountPartView) {
        Writer.simplePrint("======================================");
        Writer.simplePrint(
                formattedTotalAmount(
                        "총구매액",
                        amountPartView.getTotalQuantity(),
                        amountPartView.getTotalAmount(),
                        AMOUNT
                )
        );
        Writer.simplePrint(
                formattedAmount("행사할인", amountPartView.getPromotionDiscount(), AMOUNT)
        );
        Writer.simplePrint(
                formattedAmount("멤버십할인", amountPartView.getMembershipDiscount(), AMOUNT)
        );
        Writer.simplePrint(
                formattedAmount("내실돈", amountPartView.getPayment(), PAYMENT)
        );
    }
}
