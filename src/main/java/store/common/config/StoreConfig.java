package store.common.config;

import store.common.support.StoreMapper;
import store.infra.file.DataInitializer;
import store.infra.file.FileDataInitializer;
import store.infra.file.FileLoader;
import store.ConvenicenceStore;
import store.Store;
import store.presentation.controller.ConvenienceStoreFront;
import store.presentation.controller.ProductController;
import store.presentation.controller.PromotionController;
import store.presentation.controller.PurchaseController;
import store.presentation.controller.StoreFront;
import store.presentation.view.ApplicationView;
import store.presentation.view.InputView;
import store.presentation.view.OutputView;
import store.presentation.view.console.ApplicationConsoleView;
import store.presentation.view.console.InputConsoleView;
import store.presentation.view.console.OutputConsoleView;
import store.product.repository.InMemoryProductRepository;
import store.promotion.repository.InMemoryPromotionRepository;
import store.product.repository.ProductRepository;
import store.promotion.repository.PromotionRepository;
import store.purchase.repository.InMemoryPurchaseInfoRepository;
import store.purchase.repository.PurchaseInfoRepository;
import store.common.validate.StoreValidator;
import store.product.service.ProductService;
import store.promotion.implement.PromotionChecker;
import store.product.implement.ProductFinder;
import store.promotion.implement.PromotionFinder;
import store.promotion.service.PromotionService;
import store.purchase.service.PurchaseInfoService;

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
        return new ApplicationConsoleView(inputView(), outputView(), new StoreMapper());
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

    private StoreFront storeService() {
        return new ConvenienceStoreFront(productController(), promotionController(), purchaseController());
    }

    private ProductController productController() {
        return new ProductController(productManager());
    }

    private PromotionController promotionController() {
        return new PromotionController(applicationView(),promotionManager(),productManager());
    }

    private PurchaseController purchaseController() {
        return new PurchaseController(productManager(), applicationView(), storeValidator(), purchaseInfoManager());
    }

    private ProductService productManager() {
        return new ProductService(productFinder());
    }

    private PromotionService promotionManager() {
        return new PromotionService(promotionFinder(), new PromotionChecker());
    }

    private PurchaseInfoService purchaseInfoManager() {
        return new PurchaseInfoService(purchaseInfoRepository());
    }

    private PurchaseInfoRepository purchaseInfoRepository() {
        return InMemoryPurchaseInfoRepository.getInstance();
    }

    private ProductFinder productFinder() {
        return new ProductFinder(productRepository());
    }

    private PromotionFinder promotionFinder() {
        return new PromotionFinder(promotionRepository());
    }

    private StoreValidator storeValidator() {
        return new StoreValidator(productFinder());
    }
}
