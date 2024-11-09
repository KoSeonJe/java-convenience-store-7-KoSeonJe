package store;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import fixture.StoreFixture;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.domain.Product;
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
            List<Product> findProducts = productRepository.findByName("콜라");
            Promotion promotion = promotionRepository.findByName("MD추천상품").orElse(null);

            assertThat(findProducts).isNotNull();
            assertThat(findProducts).hasSize(2);
            assertThat(promotion).isNotNull();
            assertThat(promotion.getBuy()).isEqualTo(1);
        });
    }
}
