package store.controller;

import java.util.List;
import store.converter.SimpleConverter;
import store.io.file.read.CustomFileReader;
import store.model.product.Product;
import store.model.promotion.Promotion;
import store.repository.ProductRepository;
import store.repository.PromotionRepository;
import store.repository.sequence.SequenceGenerator;
import store.service.InitiateService;

public class InitiateController {

    private final static String PROMOTION_FILE_ROOT = "src/main/resources/promotions.md";
    private final static String PRODUCT_FILE_ROOT = "src/main/resources/products.md";

    private final SequenceGenerator sequenceGenerator;
    private final CustomFileReader customFileReader;
    private final ProductRepository productRepository;
    private final PromotionRepository promotionRepository;
    private final InitiateService initiateService;

    public InitiateController(
            final SequenceGenerator sequenceGenerator,
            final CustomFileReader customFileReader,
            final ProductRepository productRepository,
            final PromotionRepository promotionRepository,
            final InitiateService initiateService
    ) {
        this.sequenceGenerator = sequenceGenerator;
        this.customFileReader = customFileReader;
        this.productRepository = productRepository;
        this.promotionRepository = promotionRepository;
        this.initiateService = initiateService;
    }

    public void initiateData() {
        clearRepositories();
        initiatePromotionData();
        initiateProductData();
    }

    private void initiatePromotionData() {
        List<String> lines = customFileReader.readFileLinesFrom(PROMOTION_FILE_ROOT);
        for (String line : lines) {
            List<String> tokens = SimpleConverter.stringToStringList(line);
            Promotion promotion = initiateService.createPromotionFrom(tokens);
            promotionRepository.save(promotion);
        }
    }

    private void initiateProductData() {
        List<String> offeredProducts = customFileReader.readFileLinesFrom(PRODUCT_FILE_ROOT);
        saveOfferedData(offeredProducts);
        saveNotOfferedData();
    }

    private void saveOfferedData(final List<String> lines) {
        for (String line : lines) {
            List<String> tokens = SimpleConverter.stringToStringList(line);
            Product product = initiateService.createOfferedProductFrom(tokens);
            productRepository.save(product);
        }
    }

    private void saveNotOfferedData() {
        List<Product> products = productRepository.getSingleProductsOnlyHavePromotionProducts();
        for (Product product : products) {
            Long uniqueId = sequenceGenerator.generate();
            Product normalEmptyProduct = product.copyOf(uniqueId);
            productRepository.save(normalEmptyProduct);
        }
    }

    private void clearRepositories() {
        productRepository.clear();
        promotionRepository.clear();
    }
}
