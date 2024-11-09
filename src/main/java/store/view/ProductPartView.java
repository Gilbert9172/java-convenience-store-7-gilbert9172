package store.view;

import java.util.List;
import store.model.dto.ReceiptProductPartDTO;

public class ProductPartView {

    private final List<PurchasedView> purchasedViews;
    private final List<PromotionProductView> promotionProductViews;

    private ProductPartView(final List<PurchasedView> purchasedViews,
                            final List<PromotionProductView> promotionProductViews) {
        this.purchasedViews = purchasedViews;
        this.promotionProductViews = promotionProductViews;
    }

    public static ProductPartView from(final List<PurchasedView> purchasedViews,
                                       final List<PromotionProductView> promotionProductViews) {
        return new ProductPartView(purchasedViews, promotionProductViews);
    }

    public static ProductPartView from(ReceiptProductPartDTO dto) {
        List<PurchasedView> purchased = dto.getNormalProducts()
                .stream()
                .map(PurchasedView::from)
                .toList();

        List<PromotionProductView> promotionProducts = dto.getPromotionProducts()
                .stream()
                .map(PromotionProductView::from)
                .toList();
        return new ProductPartView(purchased, promotionProducts);
    }

    public List<PurchasedView> getPurchasedViews() {
        return purchasedViews;
    }

    public List<PromotionProductView> getPromotionProductViews() {
        return promotionProductViews;
    }
}
