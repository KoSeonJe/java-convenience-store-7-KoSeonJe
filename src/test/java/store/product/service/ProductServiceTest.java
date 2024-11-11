package store.product.service;

import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.Assertions;
import fixture.StoreFixture;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.payment.domain.PurchaseItemInfo;
import store.product.domain.Product;
import store.product.repository.ProductRepository;

class ProductServiceTest {

    private ProductService productService = StoreFixture.productService();
    private ProductRepository productRepository = StoreFixture.productRepository();

    @BeforeEach
    void setUp() {
        productRepository.clear();
    }

    @DisplayName("입력한 구매 아이템 중 원하는 아이템의 프로모션 할인이 적용된 아이템을 찾을 수 있다.")
    @Test
    void findPromotionProduct() {
        //given
        productRepository.saveAll(List.of(
                Product.create("콜라", new BigDecimal(1000), 2, "탄산2+1")
        ));
        PurchaseItemInfo purchaseItemInfo = new PurchaseItemInfo("콜라", 2);
        //when
        Product product = productService.findPromotionProduct(purchaseItemInfo);
        //then
        assertThat(product).isNotNull();
        assertThat(product.getName()).isEqualTo("콜라");
        assertThat(product.getPromotionName()).isEqualTo("탄산2+1");
    }

    @DisplayName("프로모션 제품에서 주어진 양만큼 차감한다.")
    @Test
    void noRemainDeductPromotion() {
        Assertions.assertSimpleTest(() -> {
            Product product = Product.create("콜라", new BigDecimal(1000), 10, "탄산2+1");
            productService.deductPromotion(product, 8);
            assertThat(product.getQuantity()).isEqualTo(2);
        });
    }

    @DisplayName("주어진 양이 프로모션 제품보다 초과되면 모두 차감하고 남는 양을 반환한다.")
    @Test
    void deductPromotion() {
        Assertions.assertSimpleTest(() -> {
            Product product = Product.create("콜라", new BigDecimal(1000), 10, "탄산2+1");
            int remain = productService.deductPromotion(product, 13);
            assertThat(remain).isEqualTo(3);
        });
    }

    @DisplayName("일반 제품에서 주어진 양만큼 차감한다.")
    @Test
    void deductOrigin() {
        Assertions.assertSimpleTest(() -> {
            Product product = Product.create("콜라", new BigDecimal(1000), 10, "탄산2+1");
            productService.deductOrigin(product, 5);
            assertThat(product.getQuantity()).isEqualTo(5);
        });
    }
}
