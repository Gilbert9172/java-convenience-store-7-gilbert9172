package store.controller;

import static store.io.terminal.helper.Retry.doWhileTemplate;
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

    public ConvenienceController(
            final InputTerminal inputTerminal,
            final OutputTerminal outputTerminal,
            final OrderService orderService,
            final PaymentService paymentService,
            final StockManageService stockManageService,
            final ProductRepository productRepository,
            final OrderFeedBackInputFactory orderFeedBackInputFactory
    ) {
        this.inputTerminal = inputTerminal;
        this.outputTerminal = outputTerminal;
        this.orderService = orderService;
        this.paymentService = paymentService;
        this.stockManageService = stockManageService;
        this.productRepository = productRepository;
        this.orderFeedBackInputFactory = orderFeedBackInputFactory;
    }


    public void run() {
        doWhileTemplate(
                () -> {
                    showProductsStock();
                    Orders orders = makeUserOrder();
                    updateOrdersByFeedBack(orders);
                    decreaseStock(orders);
                    offerReceipt(orders);
                },
                this::readUserFeedBackForStay
        );
    }

    private void showProductsStock() {
        Products products = productRepository.findAll();
        outputTerminal.printProductsStock(products.mapToView());
    }

    private Orders generateOrdersFrom(final List<PreOrderDTO> preOrders) {
        return orderService.generateOrders(preOrders);
    }

    private void updateOrdersByFeedBack(final Orders orders) {
        orders.readOnlyStream()
                .forEach(order -> {
                    UserFeedBack feedBack = orderFeedBackInputFactory.readFeedBackAbout(order, inputTerminal);
                    orderService.updateOrderByFeedBack(order, feedBack);
                });
    }

    private UserFeedBack membershipDiscountFeedBack() {
        return retryTemplate(inputTerminal::readUserFeedBackForMembershipDC);
    }

    private UserFeedBack readUserFeedBackForStay() {
        return retryTemplate(inputTerminal::readUserFeedBackForBuyMore);
    }

    private Orders makeUserOrder() {
        return retryTemplate(() -> {
            List<PreOrderDTO> preOrders = inputTerminal.readUserPreOrders();
            return generateOrdersFrom(preOrders);
        });
    }

    private void offerReceipt(final Orders orders) {
        UserFeedBack memberShipFeedBack = this.membershipDiscountFeedBack();
        ReceiptDTO receiptDTO = paymentService.offerReceipt(orders, memberShipFeedBack);
        ReceiptView receiptView = ReceiptView.from(receiptDTO);
        outputTerminal.printReceipt(receiptView);
    }

    private void decreaseStock(Orders orders) {
        stockManageService.updateStocks(orders);
    }
}
