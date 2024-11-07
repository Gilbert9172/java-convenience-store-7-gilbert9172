package store.model.order.factory.modify;

import store.model.order.Order;

public interface OrderModifier {

    void modify(Order order, OrderModifyFlag fixFlag);

}
