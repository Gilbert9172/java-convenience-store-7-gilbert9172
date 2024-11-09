package store.controller;

import java.time.LocalDateTime;
import java.util.List;
import store.io.terminal.InputTerminal;
import store.io.terminal.OutputTerminal;
import store.model.dto.PreOrderDTO;
import store.model.dto.ReceiptDTO;
import store.model.order.Order;
import store.model.order.Orders;
import store.model.order.factory.modify.UserFeedBack;
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
        List<Order> orders = generateOrders();
        updateOrders(orders);
        UserFeedBack memberShipFeedBack = this.memberShipDiscountFeedBack();
        stockManageService.updateProductStocks(Orders.of(orders));

        ReceiptDTO receiptDTO = offerReceipt(orders, memberShipFeedBack);
        ReceiptView receiptView = ReceiptView.from(receiptDTO);
        outputTerminal.printReceipt(receiptView);
    }

    private List<Order> generateOrders() {
        PreOrderDTO preOrderDTO1 = PreOrderDTO.of("콜라", 10, LocalDateTime.now());
        PreOrderDTO preOrderDTO2 = PreOrderDTO.of("오렌지주스", 7, LocalDateTime.now());
        PreOrderDTO preOrderDTO3 = PreOrderDTO.of("물", 5, LocalDateTime.now());
        List<PreOrderDTO> orderDTOS = List.of(preOrderDTO1, preOrderDTO2, preOrderDTO3);
        return orderService.generateOrders(orderDTOS);
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

    private UserFeedBack memberShipDiscountFeedBack() {
        return inputTerminal.readUserFeedBackForMemberShipDC();
    }

    private ReceiptDTO offerReceipt(final List<Order> orders, final UserFeedBack feedBack) {
        return paymentService.offerReceipt(Orders.of(orders), feedBack);
    }
}
