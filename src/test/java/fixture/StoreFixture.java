package fixture;

import store.common.support.StoreMapper;
import store.infra.file.FileDataInitializer;
import store.infra.file.FileLoader;
import store.product.implement.ProductFinder;
import store.product.repository.InMemoryProductRepository;
import store.product.service.ProductService;
import store.promotion.implement.PromotionChecker;
import store.promotion.implement.PromotionFinder;
import store.promotion.repository.InMemoryPromotionRepository;
import store.promotion.service.PromotionService;

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

    public static StoreMapper storeMapper() {
        return new StoreMapper();
    }

    public static PromotionService promotionService() {
        return new PromotionService(promotionFinder(), new PromotionChecker());
    }

    public static PromotionFinder promotionFinder() {
        return new PromotionFinder(promotionRepository());
    }

    public static ProductService productService() {
        return new ProductService(productFinder());
    }

    private static ProductFinder productFinder() {
        return new ProductFinder(productRepository());
    }
}
