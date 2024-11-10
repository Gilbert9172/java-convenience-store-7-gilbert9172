package store.io.terminal;

public enum FormatProperty {
    LEFT(18, 3, "%-?s"),
    MID(7, 2, "%-?s"),
    RIGHT(7, 5, "%?s");

    private final int value;
    private final int standard;
    private final String template;

    FormatProperty(final int value, final int standard, final String template) {
        this.value = value;
        this.standard = standard;
        this.template = template;
    }

    public static String getFormat(final String productName, final String amount) {
        String leftLineTemplate = leftLineFormat(productName);
        String rightLineFormat = rightLineFormat(amount);
        return leftLineTemplate + " %-7s " + rightLineFormat;
    }

    public static String leftLineFormat(final String productName) {
        int leftStandard = LEFT.standard;
        int currentProperty = LEFT.value;
        if (productName.length() > leftStandard) {
            currentProperty = LEFT.value - (productName.length() - leftStandard);
        }
        if (productName.length() < leftStandard) {
            currentProperty = LEFT.value + (leftStandard - productName.length());
        }
        return LEFT.template.replace("?", String.valueOf(currentProperty));
    }

    private static String rightLineFormat(final String amount) {
        int rightStandard = RIGHT.standard;
        int currentProperty = RIGHT.value;

        if (amount.length() > rightStandard) {
            currentProperty = RIGHT.value + (amount.length() - rightStandard);
        }
        if (amount.length() < rightStandard) {
            currentProperty = RIGHT.value - (rightStandard - amount.length());
        }
        return RIGHT.template.replace("?", String.valueOf(currentProperty));
    }

    public static String paymentFormat(final String productName, final String amount) {
        int rightStandard = RIGHT.standard;
        int currentProperty = RIGHT.value;

        if (amount.length() > rightStandard) {
            currentProperty = RIGHT.value + (amount.length() - rightStandard);
        }
        if (amount.length() < rightStandard) {
            currentProperty = RIGHT.value - (rightStandard - amount.length());
        }
        String replace = RIGHT.template.replace("?", String.valueOf(currentProperty + 1));
        return leftLineFormat(productName) + " %-7s " + replace;
    }
}
