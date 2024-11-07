package store.repository;

public enum SingleTonPromotionRepo {
    INSTANCE;

    private static class SingleTonPromotionRepoHolder {
        private static final PromotionRepository INSTANCE = new PromotionRepository();
    }

    public static PromotionRepository getInstance() {
        return SingleTonPromotionRepoHolder.INSTANCE;
    }
}
