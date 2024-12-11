package store.model.discount;

import static store.model.discount.DiscountType.DEFAULT;
import static store.model.discount.DiscountType.MEMBERSHIP;
import static store.model.discount.DiscountType.PROMOTION;

import java.util.EnumMap;
import java.util.Map;
import store.model.money.Money;
import store.model.order.Orders;

public class DiscountPolicyFactory {

    private static final Map<DiscountType, DiscountPolicy> discountPolicyMap = new EnumMap<>(DiscountType.class);

    static {
        discountPolicyMap.put(PROMOTION, new PromotionDiscount());
        discountPolicyMap.put(MEMBERSHIP, new MemberShipDiscount());
        discountPolicyMap.put(DEFAULT, new DefaultDiscount());
    }

    public Money applyDiscountByType(final DiscountType type, final Orders orders) {
        if (type.isPromotionType()) {
            DiscountPolicy discountPolicy = discountPolicyMap.get(PROMOTION);
            return discountPolicy.applyDiscount(orders);
        }
        if (type.isMembershipType()) {
            DiscountPolicy discountPolicy = discountPolicyMap.get(MEMBERSHIP);
            return discountPolicy.applyDiscount(orders);
        }
        DiscountPolicy discountPolicy = discountPolicyMap.get(DEFAULT);
        return discountPolicy.applyDiscount(orders);
    }
}
