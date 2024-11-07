package store.config;

import store.controller.ConvenienceController;
import store.controller.InitiateController;
import store.io.file.CustomFileReader;
import store.io.file.MarkDownReader;
import store.model.order.factory.generate.NormalOrderFactory;
import store.model.order.factory.generate.PromotionOrderFactory;
import store.model.order.factory.modify.OrderModifierFactory;
import store.repository.ProductRepository;
import store.repository.PromotionRepository;
import store.repository.SingleTonProductRepo;
import store.repository.SingleTonPromotionRepo;
import store.service.OrderService;

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

    private PromotionOrderFactory promotionOrderFactory() {
        return new PromotionOrderFactory();
    }

    private NormalOrderFactory normalOrderFactory() {
        return new NormalOrderFactory();
    }

    private OrderModifierFactory orderModifierFactory() {
        return new OrderModifierFactory();
    }

    private OrderService convenienceService() {
        return new OrderService(
                productRepository(),
                promotionOrderFactory(),
                normalOrderFactory(),
                orderModifierFactory()
        );
    }
}
