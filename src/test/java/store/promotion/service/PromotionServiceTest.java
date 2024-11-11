package store.promotion.service;

import static org.assertj.core.api.Assertions.assertThat;

import fixture.StoreFixture;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.payment.domain.PurchaseItemInfo;
import store.product.domain.Product;
import store.promotion.domain.Promotion;
import store.promotion.repository.InMemoryPromotionRepository;
import store.promotion.repository.PromotionRepository;

class PromotionServiceTest {

    private PromotionService promotionService = StoreFixture.promotionService();
    private PromotionRepository promotionRepository = InMemoryPromotionRepository.getInstance();

    @BeforeEach
    void setUp() {
        promotionRepository.clear();
    }

    @DisplayName("제품이 해당하는 프로모션의 기간이 현재 시간과 겹칠 경우 참을 반환한다.")
    @Test
    void isDiscountActive() {
        // given
        Product product = Product.create("콜라", null, 0, "할인행사");
        LocalDate start = LocalDate.parse("2024-01-01");
        LocalDate end = LocalDate.parse("2024-12-31");
        Promotion promotion = Promotion.create("할인행사", 0, 0, start, end);
        promotionRepository.saveAll(List.of(promotion));
        // when
        boolean result = promotionService.isDiscountActive(product, LocalDateTime.of(2024, 10, 12, 12, 12));
        // then
        assertThat(result).isTrue();
    }

    @DisplayName("고객이 입력한 수량이 프로모션 수량에 해당하는 경우 참을 반환한다.")
    @Test
    void checkAddPromotionQuantity() {
        // given
        Product product = Product.create("콜라", null, 10, "할인행사");
        Promotion promotion = Promotion.create("할인행사", 2, 1, null, null);
        promotionRepository.saveAll(List.of(promotion));
        PurchaseItemInfo purchaseItemInfo = new PurchaseItemInfo("콜라", 2);
        // when
        boolean result = promotionService.checkAddPromotionQuantity(product, purchaseItemInfo);
        // then
        Assertions.assertThat(result).isTrue();
    }
}
