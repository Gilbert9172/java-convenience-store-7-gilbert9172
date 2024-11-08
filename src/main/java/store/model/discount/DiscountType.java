package store.model.discount;

public enum DiscountType {

    PROMOTION,
    MEMBERSHIP,
    DEFAULT;

    public boolean isPromotionType() {
        return this == PROMOTION;
    }

    public boolean isMembershipType() {
        return this == MEMBERSHIP;
    }
}
