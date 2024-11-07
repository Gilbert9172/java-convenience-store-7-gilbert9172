package store.repository;

public enum SingleTonProductRepo {
    INSTANCE;

    private static class SingleTonProductRepoHolder {
        private static final ProductRepository INSTANCE = new ProductRepository();
    }

    public static ProductRepository getInstance() {
        return SingleTonProductRepoHolder.INSTANCE;
    }
}
