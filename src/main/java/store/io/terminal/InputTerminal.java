package store.io.terminal;

import java.util.List;
import store.io.converter.IOConverter;
import store.io.validator.order.InputValidator;
import store.model.dto.PreOrderDTO;
import store.model.order.factory.modify.UserFeedBack;

public class InputTerminal {

    private static final String ENTER_YES_OR_NO_FOR_GRAB_MORE = "현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)";
    private static final String ENTER_YES_OR_NO_FOR_OUT_OF_STOCK = "현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)";
    private static final String ENTER_YES_OR_NO_FOR_MEMBERSHIP_DC = "멤버십 할인을 받으시겠습니까? (Y/N)";
    private static final String ENTER_YOUR_ORDERS = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";
    private static final String ENTER_BUY_MORE = "감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)";


    private final Reader reader;

    private InputTerminal(final Reader reader) {
        this.reader = reader;
    }

    public static class TerminalHolder {
        private static final InputTerminal INSTANCE = new InputTerminal(
                Reader.initiate()
        );
    }

    public static InputTerminal getInstance() {
        return TerminalHolder.INSTANCE;
    }

    public List<PreOrderDTO> readUserPreOrders() {
        String message = String.format(ENTER_YOUR_ORDERS);
        Writer.printWithNewLineBefore(message);
        String input = reader.readInput();
        InputValidator.validateOrderInput(input);
        return IOConverter.toPreOrderDTOListFrom(input);
    }

    public UserFeedBack readUserFeedBackForGrabMore(final String name, final long quantity) {
        String message = String.format(ENTER_YES_OR_NO_FOR_GRAB_MORE, name, quantity);
        Writer.printWithNewLineBefore(message);
        String input = reader.readInput();
        InputValidator.validateUserFeedBack(input);
        return UserFeedBack.valueOf(input);
    }

    public UserFeedBack readUserFeedBackForOutOfStock(final String name, final long quantity) {
        String message = String.format(ENTER_YES_OR_NO_FOR_OUT_OF_STOCK, name, quantity);
        Writer.printWithNewLineBefore(message);
        String input = reader.readInput();
        InputValidator.validateUserFeedBack(input);
        return UserFeedBack.valueOf(input);
    }

    public UserFeedBack readUserFeedBackForMembershipDC() {
        Writer.printWithNewLineBefore(ENTER_YES_OR_NO_FOR_MEMBERSHIP_DC);
        String input = reader.readInput();
        InputValidator.validateUserFeedBack(input);
        return UserFeedBack.valueOf(input);
    }

    public UserFeedBack readUserFeedBackForBuyMore() {
        Writer.printWithNewLineBefore(ENTER_BUY_MORE);
        String input = reader.readInput();
        InputValidator.validateUserFeedBack(input);
        return UserFeedBack.valueOf(input);
    }
}
