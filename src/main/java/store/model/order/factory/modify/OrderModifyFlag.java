package store.model.order.factory.modify;

public enum OrderModifyFlag {

    Y(1),
    N(0);

    private final int value;

    OrderModifyFlag(final int value) {
        this.value = value;
    }

    public boolean yes() {
        return this == Y;
    }

    public boolean no() {
        return this == N;
    }
}
