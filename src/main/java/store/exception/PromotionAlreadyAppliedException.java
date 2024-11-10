package store.exception;

import static store.error.ErrorMessage.PROMOTION_ALREADY_APPLIED;

public class PromotionAlreadyAppliedException extends BusinessException {
    private PromotionAlreadyAppliedException(String message) {
        super(message);
    }

    public static PromotionAlreadyAppliedException alreadyApplied() {
        throw new PromotionAlreadyAppliedException(PROMOTION_ALREADY_APPLIED);
    }
}
