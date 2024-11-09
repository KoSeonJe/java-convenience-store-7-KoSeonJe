package store;

import store.common.config.StoreConfig;
import store.presentation.controller.Store;

public class Application {
    public static void main(String[] args) {
        StoreConfig storeConfig = StoreConfig.getInstance();
        storeConfig.dataInitializer().init();
        Store store = storeConfig.store();
        store.open();
    }
}
