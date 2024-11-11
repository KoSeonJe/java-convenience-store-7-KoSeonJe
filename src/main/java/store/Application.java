package store;

import store.common.config.StoreConfig;
import store.payment.repository.InMemoryPurchaseInfoRepository;
import store.payment.repository.ReceiptRepository;
import store.product.repository.InMemoryProductRepository;
import store.promotion.repository.InMemoryPromotionRepository;

public class Application {
    public static void main(String[] args) {
        StoreConfig storeConfig = StoreConfig.getInstance();
        storeConfig.dataInitializer().init();
        Store store = storeConfig.store();
        store.sell();
        InMemoryPurchaseInfoRepository.getInstance().clear();
        ReceiptRepository.getInstance().clear();
        InMemoryProductRepository.getInstance().clear();
        InMemoryPromotionRepository.getInstance().clear();
    }
}
