package store.io.terminal;

public class Writer {

    private Writer() {
    }

    public static void printErrorMessage(final String error) {
        simplePrint(error);
    }

    public static void simplePrint(final String message) {
        System.out.println(message);
    }

    public static void simplePrintf(String format, Object... args) {
        System.out.printf(format, args);
        System.out.println();
    }

    public static void printWithNewLineBefore(final String message) {
        printEmptyLine();
        simplePrint(message);
    }

    public static void printWithNewLineAfter(final String message) {
        simplePrint(message);
        printEmptyLine();
    }

    private static void printEmptyLine() {
        System.out.println();
    }
}
