package store.model.order.factory.modify;

import store.model.order.Order;

public class OutOfStockModifier implements OrderModifier {

    @Override
    public void modify(final Order order, final OrderModifyFlag modifyFlag) {
        if (modifyFlag.no()) {
            order.updateQuantityNoCondition();
        }
    }
}
