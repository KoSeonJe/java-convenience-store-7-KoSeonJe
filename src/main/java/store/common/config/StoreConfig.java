package store.common.config;

import store.presentation.controller.ConvenicenceStore;
import store.presentation.controller.Store;
import store.service.ProductService;
import store.service.implement.ProductFinder;
import store.service.ConvenienceProductService;
import store.common.support.StoreMapper;
import store.infra.file.DataInitializer;
import store.infra.file.FileDataInitializer;
import store.infra.file.FileLoader;
import store.repository.InMemoryProductRepository;
import store.repository.InMemoryPromotionRepository;
import store.repository.ProductRepository;
import store.repository.PromotionRepository;
import store.presentation.view.console.ApplicationConsoleView;
import store.presentation.view.ApplicationView;
import store.presentation.view.console.InputConsoleView;
import store.presentation.view.InputView;
import store.presentation.view.console.OutputConsoleView;
import store.presentation.view.OutputView;

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
        return new ConvenicenceStore(applicationView(), storeService(), new StoreMapper());
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

    private ProductService storeService() {
        return new ConvenienceProductService(productFinder());
    }

    private ProductFinder productFinder() {
        return new ProductFinder(productRepository());
    }
}
