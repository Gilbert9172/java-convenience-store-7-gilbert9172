package store.controller;

import java.time.LocalDateTime;
import java.util.List;
import store.converter.SimpleConverter;
import store.converter.TimeConverter;
import store.io.file.CustomFileReader;
import store.model.money.Money;
import store.model.order.Quantity;
import store.model.product.Product;
import store.model.promotion.Promotion;
import store.model.promotion.PromotionId;
import store.repository.ProductRepository;
import store.repository.PromotionRepository;

public class InitiateController {

    private final CustomFileReader customFileReader;
    private final ProductRepository productRepository;
    private final PromotionRepository promotionRepository;

    public InitiateController(
            final CustomFileReader customFileReader,
            final ProductRepository productRepository,
            final PromotionRepository promotionRepository) {
        this.customFileReader = customFileReader;
        this.productRepository = productRepository;
        this.promotionRepository = promotionRepository;
    }

    public void initiateData() {
        initiatePromotionData();
        initiateProductData();
    }

    private void initiatePromotionData() {
        List<String> lines = customFileReader.readFileLinesFrom("src/main/resources/promotions.md");
        for (String line : lines) {
            List<String> tokens = SimpleConverter.stringToStringList(line);

            String title = tokens.get(0);
            PromotionId promotionId = PromotionId.findByType(title);
            Quantity buy = SimpleConverter.stringToQuantity(tokens.get(1));
            Quantity get = SimpleConverter.stringToQuantity(tokens.get(2));
            LocalDateTime startDate = TimeConverter.toStartDate(tokens.get(3));
            LocalDateTime endDate = TimeConverter.toEndDate(tokens.get(4));

            Promotion promotion = Promotion.of(promotionId, title, buy, get, startDate, endDate);
            promotionRepository.save(promotion);
        }
    }

    private void initiateProductData() {
        List<String> lines = customFileReader.readFileLinesFrom("src/main/resources/products.md");
        for (String line : lines) {
            List<String> tokens = SimpleConverter.stringToStringList(line);

            String name = tokens.get(0);
            Money money = Money.from(Long.parseLong(tokens.get(1)));
            Quantity quantity = SimpleConverter.stringToQuantity(tokens.get(2));

            PromotionId promotionId = PromotionId.findByType(tokens.get(3));
            Promotion promotion = promotionRepository.findById(promotionId).orElse(null);

            Product product = Product.of(name, money, quantity, promotion);
            productRepository.save(product);
        }
    }
}
