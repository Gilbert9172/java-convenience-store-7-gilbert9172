package store.model.dto;

import java.util.List;

public class ReceiptProductPartDTO {

    private final List<PurchasedDTO> normalProducts;
    private final List<PromotionProductDTO> promotionProducts;

    private ReceiptProductPartDTO(final List<PurchasedDTO> normalProducts,
                                  final List<PromotionProductDTO> promotionProducts) {
        this.normalProducts = normalProducts;
        this.promotionProducts = promotionProducts;
    }

    public static ReceiptProductPartDTO of(final List<PurchasedDTO> normalProducts,
                                           final List<PromotionProductDTO> promotionProducts) {
        return new ReceiptProductPartDTO(normalProducts, promotionProducts);
    }

    public List<PurchasedDTO> getNormalProducts() {
        return normalProducts;
    }

    public List<PromotionProductDTO> getPromotionProducts() {
        return promotionProducts;
    }
}
