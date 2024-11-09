package store.controller;

import static store.io.terminal.helper.Retry.retryTemplate;

import java.util.List;
import store.io.terminal.InputTerminal;
import store.io.terminal.OutputTerminal;
import store.io.terminal.factory.OrderFeedBackInputFactory;
import store.model.dto.PreOrderDTO;
import store.model.dto.ReceiptDTO;
import store.model.order.Orders;
import store.model.order.factory.modify.UserFeedBack;
import store.model.product.Products;
import store.repository.ProductRepository;
import store.service.OrderService;
import store.service.PaymentService;
import store.service.StockManageService;
import store.view.ReceiptView;

public class ConvenienceController {

    private final InputTerminal inputTerminal;
    private final OutputTerminal outputTerminal;
    private final OrderService orderService;
    private final PaymentService paymentService;
    private final StockManageService stockManageService;
    private final ProductRepository productRepository;
    private final OrderFeedBackInputFactory orderFeedBackInputFactory;

    public ConvenienceController(final InputTerminal inputTerminal,
                                 final OutputTerminal outputTerminal,
                                 final OrderService orderService,
                                 final PaymentService paymentService,
                                 final StockManageService stockManageService,
                                 final ProductRepository productRepository,
                                 final OrderFeedBackInputFactory orderFeedBackInputFactory) {
        this.inputTerminal = inputTerminal;
        this.outputTerminal = outputTerminal;
        this.orderService = orderService;
        this.paymentService = paymentService;
        this.stockManageService = stockManageService;
        this.productRepository = productRepository;
        this.orderFeedBackInputFactory = orderFeedBackInputFactory;
    }


    public void run() {
        UserFeedBack buy = UserFeedBack.Y;
        while (buy.responseYes()) {
            showProductsStock();
            Orders orders = makeUserOrder();

            updateOrders(orders);
            UserFeedBack memberShipFeedBack = this.membershipDiscountFeedBack();

            stockManageService.updateProductStocks(orders);
            ReceiptDTO receiptDTO = offerReceipt(orders, memberShipFeedBack);
            ReceiptView receiptView = ReceiptView.from(receiptDTO);
            outputTerminal.printReceipt(receiptView);

            buy = readUserFeedBackForBuyMore();
        }
    }

    private void showProductsStock() {
        Products products = productRepository.findAll();
        outputTerminal.printProductsStock(products.mapToView());
    }

    private Orders generateOrdersFrom(List<PreOrderDTO> preOrders) {
        return orderService.generateOrders(preOrders);
    }

    private void updateOrders(final Orders orders) {
        orders.readOnlyStream()
                .forEach(order -> {
                    UserFeedBack flag = orderFeedBackInputFactory.readFeedBackAbout(order, inputTerminal);
                    orderService.updateOrder(order, flag);
                });
    }

    private UserFeedBack membershipDiscountFeedBack() {
        return retryTemplate(inputTerminal::readUserFeedBackForMembershipDC);
    }

    private UserFeedBack readUserFeedBackForBuyMore() {
        return retryTemplate(inputTerminal::readUserFeedBackForBuyMore);
    }

    private Orders makeUserOrder() {
        return retryTemplate(() -> {
            List<PreOrderDTO> preOrders = inputTerminal.readUserPreOrders();
            return generateOrdersFrom(preOrders);
        });
    }

    private ReceiptDTO offerReceipt(final Orders orders, final UserFeedBack feedBack) {
        return paymentService.offerReceipt(orders, feedBack);
    }
}
