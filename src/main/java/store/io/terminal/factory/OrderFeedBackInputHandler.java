package store.io.terminal.factory;

import store.io.terminal.InputTerminal;
import store.model.order.Order;
import store.model.order.factory.modify.UserFeedBack;

public interface OrderFeedBackInputHandler {
    UserFeedBack readFeedBackAbout(final Order order, final InputTerminal inputTerminal);
}
