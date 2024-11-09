package fixture;

import store.infra.file.FileDataInitializer;
import store.infra.file.FileLoader;
import store.repository.InMemoryProductRepository;
import store.repository.InMemoryPromotionRepository;
import store.infra.file.StoreFileConverter;

public class StoreFixture {

    public static FileDataInitializer fileDataInitializer() {
        return new FileDataInitializer(new FileLoader(), new StoreFileConverter(), productRepository(),
                promotionRepository());
    }

    public static InMemoryProductRepository productRepository() {
        return InMemoryProductRepository.getInstance();
    }

    public static InMemoryPromotionRepository promotionRepository() {
        return InMemoryPromotionRepository.getInstance();
    }
}
