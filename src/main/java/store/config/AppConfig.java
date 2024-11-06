package store.config;

import store.controller.InitiateController;
import store.io.file.CustomFileReader;
import store.io.file.MarkDownReader;
import store.repository.ProductRepository;
import store.repository.PromotionRepository;

public class AppConfig {

    private AppConfig() {
    }

    private static class AppConfigHolder {
        private final static AppConfig INSTANCE = new AppConfig();
    }

    public static AppConfig getInstance() {
        return AppConfigHolder.INSTANCE;
    }

    public InitiateController initiateController() {
        return new InitiateController(
                customFileReader(),
                productRepository(),
                promotionRepository()
        );
    }

    private CustomFileReader customFileReader() {
        return new MarkDownReader();
    }

    private ProductRepository productRepository() {
        return new ProductRepository();
    }

    private PromotionRepository promotionRepository() {
        return new PromotionRepository();
    }


}
