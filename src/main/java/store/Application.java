package store;

import store.common.config.StoreConfig;

public class Application {
    public static void main(String[] args) {
        StoreConfig storeConfig = StoreConfig.getInstance();
        storeConfig.dataInitializer().init();
        Store store = storeConfig.store();
        store.sell();
    }
}
