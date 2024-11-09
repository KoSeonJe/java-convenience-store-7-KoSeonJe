package fixture;

import store.FileDataInitializer;
import store.FileLoader;
import store.InMemoryProductRepository;
import store.InMemoryPromotionRepository;
import store.ObjectMapper;

public class StoreFixture {

    public static FileDataInitializer fileDataInitializer() {
        return new FileDataInitializer(new FileLoader(), new ObjectMapper(), productRepository(),
                promotionRepository());
    }

    public static InMemoryProductRepository productRepository() {
        return InMemoryProductRepository.getInstance();
    }

    public static InMemoryPromotionRepository promotionRepository() {
        return InMemoryPromotionRepository.getInstance();
    }
}
