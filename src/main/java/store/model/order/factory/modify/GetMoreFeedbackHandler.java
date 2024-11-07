package store.model.order.factory.modify;

import store.model.order.Order;

public class GetMoreFeedbackHandler implements OrderFeedbackHandler {

    @Override
    public void modify(final Order order, final UserFeedBack feedBack) {
        if (feedBack.responseYes()) {
            order.applyPositiveFeedBack();
        }
    }
}
