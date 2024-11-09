package store.io.error;

public class ErrorMessage {

    private ErrorMessage() {
    }

    public static final String INVALID_ORDER_REGEX = "올바르지 않은 주문형식 입니다. (예: [사이다-2],[감자칩-1])";
    public static final String INVALID_USER_FEED_BACK_REGEX = "올바르지 않은 응답 값 입니다. (예: Y/N)";
}
