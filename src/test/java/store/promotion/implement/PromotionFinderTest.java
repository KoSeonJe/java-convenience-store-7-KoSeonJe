package store.promotion.implement;

import fixture.StoreFixture;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.payment.domain.PurchaseItemInfo;
import store.product.domain.Product;
import store.promotion.domain.Promotion;
import store.promotion.repository.PromotionRepository;

class PromotionFinderTest {

    private final PromotionFinder promotionFinder = StoreFixture.promotionFinder();
    private final PromotionRepository promotionRepository = StoreFixture.promotionRepository();

    @BeforeEach
    void setUp() {
        promotionRepository.clear();
    }

    @DisplayName("정가로 계산하는 제품의 양을 반환한다.")
    @Test
    void findNonDiscountedQuantity() {
        //given
        Promotion promotion = Promotion.create("할인행사", 2, 1, null, null);
        promotionRepository.saveAll(List.of(promotion));
        Product product = Product.create("콜라", null, 10, "할인행사");
        PurchaseItemInfo purchaseInfo = new PurchaseItemInfo("콜라", 8);
        //when
        int quantity = promotionFinder.findNonDiscountedQuantity(product, purchaseInfo);
        //then
        Assertions.assertThat(quantity).isEqualTo(1);
    }
}
