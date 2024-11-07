package store.config;

import store.controller.ConvenienceController;
import store.controller.InitiateController;
import store.io.file.CustomFileReader;
import store.io.file.MarkDownReader;
import store.repository.ProductRepository;
import store.repository.PromotionRepository;
import store.repository.SingleTonProductRepo;
import store.repository.SingleTonPromotionRepo;
import store.service.ConvenienceService;

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

    public ConvenienceController convenienceController() {
        return new ConvenienceController(
                convenienceService()
        );
    }


    private CustomFileReader customFileReader() {
        return new MarkDownReader();
    }

    private ProductRepository productRepository() {
        return SingleTonProductRepo.getInstance();
    }

    private PromotionRepository promotionRepository() {
        return SingleTonPromotionRepo.getInstance();
    }

    private ConvenienceService convenienceService() {
        return new ConvenienceService(
                productRepository(),
                promotionRepository()
        );
    }
}
