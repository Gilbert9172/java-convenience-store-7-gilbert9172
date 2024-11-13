package store.io.terminal.factory;

import static store.io.terminal.helper.Retry.retryTemplate;

import store.io.terminal.InputTerminal;
import store.model.order.Order;
import store.model.order.factory.modify.UserFeedBack;

public class GrabMoreInputHandler implements OrderFeedBackInputHandler {

    @Override
    public UserFeedBack readFeedBackAbout(final Order order, final InputTerminal inputTerminal) {
        String productName = order.purchasedProductName();
        return retryTemplate(() ->
                inputTerminal.readUserFeedBackForGrabMore(productName)
        );
    }
}
