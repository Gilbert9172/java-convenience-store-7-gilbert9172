package store.controller;

import java.util.List;
import java.util.function.Supplier;
import store.exception.BusinessException;
import store.io.terminal.InputTerminal;
import store.io.terminal.OutputTerminal;
import store.model.dto.PreOrderDTO;
import store.model.dto.ReceiptDTO;
import store.model.order.Order;
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

    public ConvenienceController(final InputTerminal inputTerminal,
                                 final OutputTerminal outputTerminal,
                                 final OrderService orderService,
                                 final PaymentService paymentService,
                                 final StockManageService stockManageService,
                                 final ProductRepository productRepository) {
        this.inputTerminal = inputTerminal;
        this.outputTerminal = outputTerminal;
        this.orderService = orderService;
        this.paymentService = paymentService;
        this.stockManageService = stockManageService;
        this.productRepository = productRepository;
    }

    public void run() {
        UserFeedBack buy = UserFeedBack.Y;
        while (buy.responseYes()) {
            showProductsStock();
            List<Order> orders = makeUserOrder();

            updateOrders(orders);
            UserFeedBack memberShipFeedBack = this.membershipDiscountFeedBack();

            stockManageService.updateProductStocks(Orders.of(orders));
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

    private List<Order> generateOrdersFrom(List<PreOrderDTO> preOrders) {
        return orderService.generateOrders(preOrders);
    }

    private void updateOrders(final List<Order> orders) {
        for (Order order : orders) {
            String productName = order.purchasedProductName();
            long quantity = order.feedBackQuantity();
            if (order.hasGrabMoreFeedBack()) {
                UserFeedBack flag = inputTerminal.readUserFeedBackForGrapMore(productName, quantity);
                orderService.updateOrder(order, flag);
            }
            if (order.hasOutOfStockFeedBack()) {
                UserFeedBack flag = inputTerminal.readUserFeedBackForOutOfStock(productName, quantity);
                orderService.updateOrder(order, flag);
            }
        }
    }

    private UserFeedBack membershipDiscountFeedBack() {
        return inputTerminal.readUserFeedBackForMembershipDC();
    }

    private UserFeedBack readUserFeedBackForBuyMore() {
        return inputTerminal.readUserFeedBackForBuyMore();
    }

    private List<Order> makeUserOrder() {
        return retryTemplate(() -> {
            List<PreOrderDTO> preOrders = inputTerminal.readUserPreOrders();
            return generateOrdersFrom(preOrders);
        });
    }

    private ReceiptDTO offerReceipt(final List<Order> orders, final UserFeedBack feedBack) {
        return paymentService.offerReceipt(Orders.of(orders), feedBack);
    }

    private <T> T retryTemplate(final Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException | BusinessException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
