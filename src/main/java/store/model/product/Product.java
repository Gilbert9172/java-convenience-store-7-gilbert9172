package store.model.product;

import store.model.money.Money;
import store.model.promotion.Promotion;

public class Product {

    private final String name;
    private final Money amount;
    private final int quantity;
    private final Promotion promotion;

    private Product(
            final String name,
            final Money amount,
            final int quantity,
            final Promotion promotion
    ) {
        this.name = name;
        this.amount = amount;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public static Product of(
            final String name,
            final Money amount,
            final int quantity,
            final Promotion promotion
    ) {
        return new Product(name, amount, quantity, promotion);
    }
}
