package store.model.order.factory.modify;

import store.model.order.Order;

public class GetMoreModifier implements OrderModifier {

    @Override
    public void modify(final Order order, final OrderModifyFlag modifyFlag) {
        if (modifyFlag.yes()) {
            order.updateQuantityYesCondition();
        }
    }
}
