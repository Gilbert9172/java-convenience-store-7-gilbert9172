package store.model.order;

import java.util.List;

public class Orders {

    private final List<Order> orders;

    private Orders(final List<Order> orders) {
        this.orders = orders;
    }

    public static Orders of(final List<Order> orders) {
        return new Orders(orders);
    }

    public List<Order> getOrders() {
        return orders;
    }
}
