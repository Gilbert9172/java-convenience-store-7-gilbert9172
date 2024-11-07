package store.model.order.factory.modify;

import store.model.order.Order;

public class OutOfStockFeedbackHandler implements OrderFeedbackHandler {

    @Override
    public void modify(final Order order, final UserFeedBack userFeedBack) {
        if (userFeedBack.responseNo()) {
            order.applyNegativeFeedBack();
        }
    }
}
