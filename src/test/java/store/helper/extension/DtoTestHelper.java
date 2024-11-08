package store.helper.extension;

import org.assertj.core.api.Assertions;
import store.model.dto.PromotionProductDTO;
import store.model.dto.PurchasedDTO;

public class DtoTestHelper {

    private DtoTestHelper() {
    }

    public static void assertPurchasedDTO(PurchasedDTO dto, Object... objects) {
        Assertions.assertThat(dto).extracting(
                        PurchasedDTO::getProductName,
                        PurchasedDTO::getQuantity,
                        PurchasedDTO::getTotalPrice)
                .containsExactly(objects[0], objects[1], objects[2]);
    }

    public static void assertPromotionProductDTO(PromotionProductDTO dto, Object... objects) {
        Assertions.assertThat(dto).extracting(
                        PromotionProductDTO::getProductName,
                        PromotionProductDTO::getQuantity)
                .containsExactly(objects[0], objects[1]);
    }

}
