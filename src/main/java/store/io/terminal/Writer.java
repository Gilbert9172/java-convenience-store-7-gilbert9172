package store.io.terminal;

public class Writer {

    private Writer() {
    }

    public static Writer initiate() {
        return new Writer();
    }

    public void printErrorMessage(final String error) {
        simplePrint(error);
    }

    public void simplePrint(final String message) {
        System.out.println(message);
    }

    public void simplePrintf(String format, Object... args) {
        System.out.printf(format, args);
        System.out.println();
    }

    public void printWithNewLineBefore(final String message) {
        printEmptyLine();
        simplePrint(message);
    }

    public void printWithNewLineAfter(final String message) {
        simplePrint(message);
        printEmptyLine();
    }

    public void printEmptyLine() {
        System.out.println();
    }
}
