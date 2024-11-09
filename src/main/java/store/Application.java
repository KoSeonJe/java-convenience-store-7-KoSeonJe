package store;

import store.common.config.StoreConfig;
import store.infra.file.DataInitializer;

public class Application {
    public static void main(String[] args) {
        StoreConfig storeConfig = StoreConfig.getInstance();
        DataInitializer dataInitializer = storeConfig.dataInitializer();
        dataInitializer.init();
    }
}
