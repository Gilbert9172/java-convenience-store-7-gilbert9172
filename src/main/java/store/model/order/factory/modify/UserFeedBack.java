package store.model.order.factory.modify;

public enum UserFeedBack {

    Y(1),
    N(0);

    private final int value;

    UserFeedBack(final int value) {
        this.value = value;
    }

    public boolean responseYes() {
        return this == Y;
    }

    public boolean responseNo() {
        return this == N;
    }
}
