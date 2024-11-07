package store.model.order.factory.modify;

import store.model.order.Order;

public interface OrderFeedbackHandler {

    void modify(Order order, UserFeedBack fixFlag);

}
