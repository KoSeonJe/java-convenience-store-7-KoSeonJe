package fixture;

import store.infra.file.FileDataInitializer;
import store.infra.file.FileLoader;
import store.product.repository.InMemoryProductRepository;
import store.promotion.repository.InMemoryPromotionRepository;
import store.common.support.StoreMapper;

public class StoreFixture {

    public static FileDataInitializer fileDataInitializer() {
        return new FileDataInitializer(new FileLoader(), new StoreMapper(), productRepository(),
                promotionRepository());
    }

    public static InMemoryProductRepository productRepository() {
        return InMemoryProductRepository.getInstance();
    }

    public static InMemoryPromotionRepository promotionRepository() {
        return InMemoryPromotionRepository.getInstance();
    }
}
