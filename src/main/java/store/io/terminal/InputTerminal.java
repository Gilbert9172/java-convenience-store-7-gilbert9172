package store.io.terminal;

import java.util.function.Supplier;
import store.exception.BusinessException;
import store.model.order.factory.modify.UserFeedBack;

public class InputTerminal {

    private static final String ENTER_YES_OR_NO_FOR_GRAP_MORE = "현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)";
    private static final String ENTER_YES_OR_NO_FOR_OUT_OF_STOCK = "현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)";
    private static final String ENTER_YES_OR_NO_FOR_MEMBERSHIP_DC = "멤버십 할인을 받으시겠습니까? (Y/N)";


    private final Writer writer;
    private final Reader reader;

    private InputTerminal(final Writer writer, final Reader reader) {
        this.writer = writer;
        this.reader = reader;
    }

    public static class TerminalHolder {
        private static final InputTerminal INSTANCE = new InputTerminal(
                Writer.initiate(),
                Reader.initiate()
        );
    }

    public static InputTerminal getInstance() {
        return TerminalHolder.INSTANCE;
    }

    public UserFeedBack readUserFeedBackForGrapMore(final String name, final long quantity) {
        return retryTemplate(() -> {
            String message = String.format(ENTER_YES_OR_NO_FOR_GRAP_MORE, name, quantity);
            writer.simplePrint(message);
            String input = reader.readInput();
            return UserFeedBack.valueOf(input);
        });
    }

    public UserFeedBack readUserFeedBackForOutOfStock(final String name, final long quantity) {
        return retryTemplate(() -> {
            String message = String.format(ENTER_YES_OR_NO_FOR_OUT_OF_STOCK, name, quantity);
            writer.simplePrint(message);
            String input = reader.readInput();
            return UserFeedBack.valueOf(input);
        });
    }

    public UserFeedBack readUserFeedBackForMemberShipDC() {
        return retryTemplate(() -> {
            writer.simplePrint(ENTER_YES_OR_NO_FOR_MEMBERSHIP_DC);
            String input = reader.readInput();
            return UserFeedBack.valueOf(input);
        });
    }

    private <T> T retryTemplate(final Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException | BusinessException e) {
                writer.printErrorMessage(e.getMessage());
            }
        }
    }
}
