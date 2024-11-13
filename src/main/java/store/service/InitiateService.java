package store.service;

import java.time.LocalDateTime;
import java.util.List;
import store.converter.SimpleConverter;
import store.converter.TimeConverter;
import store.model.money.Money;
import store.model.order.Quantity;
import store.model.product.Product;
import store.model.product.ProductId;
import store.model.promotion.Promotion;
import store.model.promotion.PromotionId;
import store.repository.PromotionRepository;
import store.repository.sequence.SequenceGenerator;

public class InitiateService {

    private final SequenceGenerator sequenceGenerator;
    private final PromotionRepository promotionRepository;

    public InitiateService(
            final PromotionRepository promotionRepository,
            final SequenceGenerator sequenceGenerator
    ) {
        this.promotionRepository = promotionRepository;
        this.sequenceGenerator = sequenceGenerator;
    }

    public Promotion createPromotionFrom(final List<String> tokens) {
        String title = tokens.get(0);
        PromotionId promotionId = PromotionId.from(sequenceGenerator.generate());
        Quantity buy = SimpleConverter.stringToQuantity(tokens.get(1));
        Quantity get = SimpleConverter.stringToQuantity(tokens.get(2));
        LocalDateTime startDate = TimeConverter.toStartDate(tokens.get(3));
        LocalDateTime endDate = TimeConverter.toEndDate(tokens.get(4));
        return Promotion.of(promotionId, title, buy, get, startDate, endDate);
    }

    public Product createOfferedProductFrom(final List<String> tokens) {
        String name = tokens.get(0);
        Money money = Money.from(Long.parseLong(tokens.get(1)));
        Quantity quantity = SimpleConverter.stringToQuantity(tokens.get(2));
        String promotionTitle = tokens.get(3);
        Long uniqueId = sequenceGenerator.generate();
        Promotion promotion = promotionRepository.findByTitle(promotionTitle).orElse(null);
        return Product.of(ProductId.from(uniqueId), name, money, quantity, promotion);
    }

}
