package store.model.order.factory.modify;

public enum UserFeedBack {

    Y(1),
    N(0);

    private final int flag;

    UserFeedBack(final int flag) {
        this.flag = flag;
    }

    public boolean responseYes() {
        return this == Y;
    }

    public boolean responseNo() {
        return this == N;
    }
}
