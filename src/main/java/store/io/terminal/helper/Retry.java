package store.io.terminal.helper;

import java.util.function.Supplier;
import store.exception.BusinessException;

public class Retry {

    private Retry() {
    }

    public static <T> T retryTemplate(final Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException | BusinessException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
