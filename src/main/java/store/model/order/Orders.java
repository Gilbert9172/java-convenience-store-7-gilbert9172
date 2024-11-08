package store.model.order;

import java.util.List;
import store.model.dto.PromotionProductDTO;
import store.model.dto.PurchasedDTO;
import store.model.money.Money;

public class Orders {

    private final List<Order> orders;

    private Orders(final List<Order> orders) {
        this.orders = orders;
    }

    public static Orders of(final List<Order> orders) {
        return new Orders(orders);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<PurchasedDTO> mapToPurchasedProducts() {
        return orders.stream()
                .map(order -> {
                    String productName = order.purchasedProductName();
                    Quantity ordrerQuantity = order.getOrdrerQuantity();
                    Money productAmount = order.getProductAmount();
                    Money totalAmount = productAmount.multiply(ordrerQuantity);
                    return PurchasedDTO.of(productName, ordrerQuantity, totalAmount);
                })
                .toList();
    }

    public List<PromotionProductDTO> mapToPromotionPrizes() {
        return orders.stream()
                .filter(Order::isPromotionProduct)
                .map(order -> {
                    String productName = order.purchasedProductName();
                    Quantity prizeCount = order.getPrizeCount();
                    return PromotionProductDTO.of(productName, prizeCount);
                })
                .toList();
    }

    public Money totalOriginalAmount() {
        List<Money> monies = orders.stream()
                .map(Order::totalPrice)
                .toList();
        return Money.addAll(monies);
    }

    public Quantity totalPurchasedQuantity() {
        List<Quantity> quantities = orders.stream()
                .map(Order::getOrdrerQuantity)
                .toList();
        return Quantity.addAll(quantities);
    }

    public Money totalPromotionProductsAmount() {
        List<Money> monies = orders.stream()
                .map(order -> {
                    Quantity prizeCount = order.getPrizeCount();
                    Money productPrice = order.getProductAmount();
                    return productPrice.multiply(prizeCount);
                })
                .toList();
        return Money.addAll(monies);
    }

    public Money totalNormalProductsAmount() {
        List<Money> monies = orders.stream()
                .filter(Order::isNormalProduct)
                .map(order -> {
                    Money productPrice = order.getProductAmount();
                    Quantity quantity = order.getNormalProductQuantity();
                    return productPrice.multiply(quantity);
                })
                .toList();
        return Money.addAll(monies);
    }
}
