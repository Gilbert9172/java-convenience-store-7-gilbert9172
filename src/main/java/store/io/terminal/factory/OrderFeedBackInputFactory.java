package store.io.terminal.factory;


import java.util.EnumMap;
import java.util.Map;
import store.io.terminal.InputTerminal;
import store.model.order.Order;
import store.model.order.OrderFeedBack.Type;
import store.model.order.factory.modify.UserFeedBack;

public class OrderFeedBackInputFactory {

    private final Map<Type, OrderFeedBackInputHandler> handlerMap = new EnumMap<>(Type.class);

    public OrderFeedBackInputFactory() {
        handlerMap.put(Type.OUT_OF_STOCK, new OutOfStockInputHandler());
        handlerMap.put(Type.GRAB_MORE, new GrabMoreInputHandler());
    }

    public UserFeedBack readFeedBackAbout(final Order order, final InputTerminal inputTerminal) {
        if (order.hasGrabMoreFeedBack()) {
            OrderFeedBackInputHandler orderFeedBackInputHandler = handlerMap.get(Type.GRAB_MORE);
            return orderFeedBackInputHandler.readFeedBackAbout(order, inputTerminal);
        }
        if (order.hasOutOfStockFeedBack()) {
            OrderFeedBackInputHandler orderFeedBackInputHandler = handlerMap.get(Type.OUT_OF_STOCK);
            return orderFeedBackInputHandler.readFeedBackAbout(order, inputTerminal);
        }
        return UserFeedBack.N;
    }
}
