package store.common.config;

import store.ConvenicenceStore;
import store.Store;
import store.application.StoreService;
import store.application.service.ConvenienceStoreFinder;
import store.application.service.ConvenienceStoreService;
import store.common.support.StoreMapper;
import store.infra.file.DataInitializer;
import store.infra.file.FileDataInitializer;
import store.infra.file.FileLoader;
import store.repository.InMemoryProductRepository;
import store.repository.InMemoryPromotionRepository;
import store.repository.ProductRepository;
import store.repository.PromotionRepository;
import store.view.ApplicationConsoleView;
import store.view.ApplicationView;
import store.view.InputConsoleView;
import store.view.InputView;
import store.view.OutputConsoleView;
import store.view.OutputView;

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

    public Store store() {
        return new ConvenicenceStore(applicationView(), storeService());
    }

    private ApplicationView applicationView() {
        return new ApplicationConsoleView(inputView(), outputView());
    }

    private InputView inputView() {
        return new InputConsoleView();
    }

    private OutputView outputView() {
        return new OutputConsoleView();
    }

    private ProductRepository productRepository() {
        return InMemoryProductRepository.getInstance();
    }

    private PromotionRepository promotionRepository() {
        return InMemoryPromotionRepository.getInstance();
    }

    private StoreService storeService() {
        return new ConvenienceStoreService(convenienceStoreFinder());
    }

    private ConvenienceStoreFinder convenienceStoreFinder() {
        return new ConvenienceStoreFinder(productRepository());
    }
}
