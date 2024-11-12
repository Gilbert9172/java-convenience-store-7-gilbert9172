package store.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import store.model.promotion.Promotion;

public class PromotionRepository implements JpaRepository<Promotion> {

    private final List<Promotion> promotions = new ArrayList<>();

    @Override
    public void save(final Promotion promotion) {
        promotions.add(promotion);
    }

    @Override
    public void clear() {
        promotions.clear();
    }

    public Optional<Promotion> findByTitle(final String title) {
        return promotions.stream()
                .filter(promotion -> promotion.hasSameTitle(title))
                .findFirst();
    }
}
