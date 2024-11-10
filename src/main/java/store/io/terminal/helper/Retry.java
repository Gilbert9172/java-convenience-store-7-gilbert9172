package store.io.terminal.helper;

import java.util.function.Supplier;
import store.exception.BusinessException;
import store.io.terminal.Writer;
import store.model.order.factory.modify.UserFeedBack;

public class Retry {

    private Retry() {
    }

    public static <T> T retryTemplate(final Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException | BusinessException e) {
                Writer.printErrorMessage(e.getMessage());
            }
        }
    }

    public static void doWhileTemplate(final Runnable runnable, final Supplier<UserFeedBack> supplier) {
        UserFeedBack userFeedBack;
        do {
            runnable.run();
            userFeedBack = supplier.get();
        } while (userFeedBack.responseYes());
    }
}
