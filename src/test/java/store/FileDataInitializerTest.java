package store;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import fixture.StoreFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.domain.ProductGroup;
import store.domain.Promotion;
import store.infra.file.FileDataInitializer;
import store.repository.InMemoryProductRepository;
import store.repository.InMemoryPromotionRepository;

class FileDataInitializerTest {

    private FileDataInitializer fileDataInitializer = StoreFixture.fileDataInitializer();
    private InMemoryProductRepository productRepository = StoreFixture.productRepository();
    private InMemoryPromotionRepository promotionRepository = StoreFixture.promotionRepository();

    @BeforeEach
    void setUp() {
        productRepository.clear();
        promotionRepository.clear();
    }

    @Test
    void init() {
        assertSimpleTest(() -> {
            fileDataInitializer.init();
            ProductGroup findProductGroup = productRepository.findByName("콜라").orElse(null);
            Promotion promotion = promotionRepository.findByName("MD추천상품").orElse(null);

            assertThat(findProductGroup).isNotNull();
            assertThat(findProductGroup.getProducts()).hasSize(2);
            assertThat(promotion).isNotNull();
            assertThat(promotion.getBuy()).isEqualTo(1);
        });
    }
}
