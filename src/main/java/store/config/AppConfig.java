package store.config;

import store.controller.ConvenienceController;
import store.controller.InitiateController;
import store.io.file.read.CustomFileReader;
import store.io.file.read.MarkDownReader;
import store.io.file.write.CustomFileWriter;
import store.io.file.write.MarkDownWriter;
import store.io.terminal.InputTerminal;
import store.io.terminal.OutputTerminal;
import store.io.terminal.factory.OrderFeedBackInputFactory;
import store.model.discount.DiscountPolicyFactory;
import store.model.order.factory.generate.NormalOrderFactory;
import store.model.order.factory.generate.PromotionOrderFactory;
import store.model.order.factory.modify.OrderFeedBackHandlerFactory;
import store.repository.ProductRepository;
import store.repository.PromotionRepository;
import store.repository.SingleTonProductRepo;
import store.repository.SingleTonPromotionRepo;
import store.service.OrderService;
import store.service.PaymentService;
import store.service.StockManageService;

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
                inputTerminal(),
                outputTerminal(),
                orderService(),
                paymentService(),
                stockManageService(),
                productRepository(),
                orderFeedBackHandlerFactory(),
                customFileWriter()
        );
    }

    private CustomFileWriter customFileWriter() {
        return new MarkDownWriter();
    }

    private InputTerminal inputTerminal() {
        return InputTerminal.getInstance();
    }

    private OutputTerminal outputTerminal() {
        return OutputTerminal.getInstance();
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

    private OrderFeedBackHandlerFactory orderModifierFactory() {
        return new OrderFeedBackHandlerFactory();
    }

    private OrderService orderService() {
        return new OrderService(
                productRepository(),
                promotionOrderFactory(),
                normalOrderFactory(),
                orderModifierFactory()
        );
    }

    private PaymentService paymentService() {
        return new PaymentService(
                discountPolicyFactory()
        );
    }

    private StockManageService stockManageService() {
        return new StockManageService(
                productRepository()
        );
    }

    private DiscountPolicyFactory discountPolicyFactory() {
        return new DiscountPolicyFactory();
    }

    private OrderFeedBackInputFactory orderFeedBackHandlerFactory() {
        return new OrderFeedBackInputFactory();
    }
}
