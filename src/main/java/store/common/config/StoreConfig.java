package store.common.config;

import store.infra.file.DataInitializer;
import store.infra.file.FileDataInitializer;
import store.infra.file.FileLoader;
import store.repository.InMemoryProductRepository;
import store.repository.InMemoryPromotionRepository;
import store.repository.ProductRepository;
import store.repository.PromotionRepository;
import store.common.StoreMapper;

public final class StoreConfig {

    private static final StoreConfig INSTANCE = new StoreConfig();

    private StoreConfig() {

    }

    public static StoreConfig getInstance() {
        return INSTANCE;
    }

    public DataInitializer dataInitializer() {
        return new FileDataInitializer(new FileLoader(), new StoreMapper(), productRepository(), promotionRepository());
    }

    private ProductRepository productRepository() {
        return InMemoryProductRepository.getInstance();
    }

    private PromotionRepository promotionRepository() {
        return InMemoryPromotionRepository.getInstance();
    }
}
