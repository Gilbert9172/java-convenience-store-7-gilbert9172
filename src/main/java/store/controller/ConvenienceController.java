package store.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;
import store.exception.BusinessException;
import store.model.dto.PreOrderDTO;
import store.model.order.Order;
import store.model.order.factory.modify.OrderModifyFlag;
import store.service.OrderService;

public class ConvenienceController {

    private final OrderService orderService;

    public ConvenienceController(final OrderService orderService) {
        this.orderService = orderService;
    }

    public void run() {
        List<Order> orders = generateOrders();
        updateOrders(orders);
    }

    private List<Order> generateOrders() {
        return retryTemplate(() -> {
            PreOrderDTO preOrderDTO1 = PreOrderDTO.of("콜라", 6, LocalDateTime.now());
            PreOrderDTO preOrderDTO2 = PreOrderDTO.of("오렌지주스", 3, LocalDateTime.now());
            List<PreOrderDTO> orderDTOS = List.of(preOrderDTO1, preOrderDTO2);
            return orderService.generateOrders(orderDTOS);
        });
    }

    private void updateOrders(final List<Order> orders) {
        for (Order order : orders) {
            retryTemplate(() -> {
                // YN을 입력 받아서
                orderService.updateOrder(order, OrderModifyFlag.Y);
                return order;
            });
        }
    }

    private <T> T retryTemplate(final Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (BusinessException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
