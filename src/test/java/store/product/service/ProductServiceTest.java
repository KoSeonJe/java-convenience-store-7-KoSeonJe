package store.product.service;

import static org.assertj.core.api.Assertions.assertThat;

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
}
