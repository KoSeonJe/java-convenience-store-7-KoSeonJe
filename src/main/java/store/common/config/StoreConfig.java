package store.common.config;

import store.ConvenicenceStore;
import store.Store;
import store.common.support.StoreMapper;
import store.common.validate.StoreValidator;
import store.infra.file.DataInitializer;
import store.infra.file.FileDataInitializer;
import store.infra.file.FileLoader;
import store.payment.implement.ReceiptFactory;
import store.payment.implement.ReceiptFinder;
import store.payment.repository.InMemoryPurchaseInfoRepository;
import store.payment.repository.PurchaseInfoRepository;
import store.payment.repository.ReceiptRepository;
import store.payment.service.PaymentService;
import store.payment.service.PurchaseInfoService;
import store.presentation.controller.ConvenienceStoreFront;
import store.presentation.controller.PaymentController;
import store.presentation.controller.PromotionController;
import store.presentation.controller.PurchaseController;
import store.presentation.controller.StoreFront;
import store.presentation.view.ApplicationView;
import store.presentation.view.InputView;
import store.presentation.view.OutputView;
import store.presentation.view.console.ApplicationConsoleView;
import store.presentation.view.console.InputConsoleView;
import store.presentation.view.console.OutputConsoleView;
import store.product.implement.ProductFinder;
import store.product.repository.InMemoryProductRepository;
import store.product.repository.ProductRepository;
import store.product.service.ProductService;
import store.promotion.implement.PromotionChecker;
import store.promotion.implement.PromotionFinder;
import store.promotion.repository.InMemoryPromotionRepository;
import store.promotion.repository.PromotionRepository;
import store.promotion.service.PromotionService;

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
        return new ConvenicenceStore(storeFront());
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

    private StoreFront storeFront() {
        return new ConvenienceStoreFront(promotionController(), purchaseController(), paymentController(), applicationView());
    }

    private PromotionController promotionController() {
        return new PromotionController(applicationView(), promotionService(), productService());
    }

    private PurchaseController purchaseController() {
        return new PurchaseController(productService(), applicationView(), storeValidator(), purchaseInfoService());
    }

    private PaymentController paymentController() {
        return new PaymentController(purchaseInfoService(), paymentService(), applicationView());
    }

    private PaymentService paymentService() {
        return new PaymentService(productService(), receiptFactory(), receiptFinder());
    }

    private ReceiptFinder receiptFinder() {
        return new ReceiptFinder(receiptRepository());
    }

    private ProductService productService() {
        return new ProductService(productFinder());
    }

    private PromotionService promotionService() {
        return new PromotionService(promotionFinder(), new PromotionChecker());
    }

    private PurchaseInfoService purchaseInfoService() {
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

    private ReceiptFactory receiptFactory() {
        return new ReceiptFactory(productFinder(), promotionFinder(), receiptRepository());
    }

    private ReceiptRepository receiptRepository() {
        return ReceiptRepository.getInstance();
    }
}
