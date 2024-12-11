package store.io.terminal;

import static store.io.terminal.ReceiptLineFormatter.FormatType.AMOUNT;
import static store.io.terminal.ReceiptLineFormatter.FormatType.PRODUCT_NAME;
import static store.io.terminal.ReceiptLineFormatter.FormatType.QUANTITY_LINE;

public class ReceiptLineFormatter {

    public enum FormatType {
        PRODUCT_NAME(18, 3, "%-?s"),
        QUANTITY_LINE(7, 2, "%-?s"),
        AMOUNT(7, 5, "%?s"),
        PAYMENT(8, 5, "%?s");

        private final int value;
        private final int standard;
        private final String template;

        FormatType(final int value, final int standard, final String template) {
            this.value = value;
            this.standard = standard;
            this.template = template;
        }
    }

    private ReceiptLineFormatter() {
    }

    public static String fixedFormat(
            final String productName,
            final String quantity,
            final String amount
    ) {
        String productNameFormatter = PRODUCT_NAME.template.replace("?", String.valueOf(PRODUCT_NAME.value));
        String quantityFormatter = QUANTITY_LINE.template.replace("?", String.valueOf(QUANTITY_LINE.value));
        String amountFormatter = AMOUNT.template.replace("?", String.valueOf(AMOUNT.value * -1));
        String formatter = productNameFormatter
                + " "
                + quantityFormatter
                + " "
                + amountFormatter;
        return String.format(formatter, productName, quantity, amount);
    }

    public static String formattedTotalAmount(
            final String leftLineSource,
            final long midLineSource,
            final String rightLineSource,
            final FormatType type
    ) {
        String formatter = leftLineFormat(leftLineSource)
                + " "
                + midLineFormat()
                + " "
                + rightLineFormat(type, rightLineSource);
        return String.format(formatter, leftLineSource, midLineSource, rightLineSource);
    }

    public static String formattedAmount(
            final String leftLineSource,
            final String rightLineSource,
            final FormatType property
    ) {
        String formatter = leftLineFormat(leftLineSource) + " %-7s " + rightLineFormat(property, rightLineSource);
        return String.format(formatter, leftLineSource, "", rightLineSource);
    }

    public static String leftLineFormat(final String productName) {
        int standard = PRODUCT_NAME.standard;
        int currentProperty = PRODUCT_NAME.value;
        if (productName.length() > standard) {
            currentProperty = PRODUCT_NAME.value - (productName.length() - standard);
        }
        if (productName.length() < standard) {
            currentProperty = PRODUCT_NAME.value + (standard - productName.length());
        }
        return PRODUCT_NAME.template.replace("?", String.valueOf(currentProperty));
    }

    public static String midLineFormat() {
        return QUANTITY_LINE.template.replace("?", "7");
    }

    public static String rightLineFormat(final FormatType type, final String amount) {
        int standard = type.standard;
        int currentProperty = type.value;

        if (amount.length() > standard) {
            currentProperty = type.value + (amount.length() - standard);
        }
        if (amount.length() < standard) {
            currentProperty = type.value - (standard - amount.length());
        }
        return type.template.replace("?", String.valueOf(currentProperty));
    }
}
