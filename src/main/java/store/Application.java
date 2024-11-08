package store;

public class Application {
    public static void main(String[] args) {
        StoreConfig storeConfig = StoreConfig.getInstance();
        DataInitializer dataInitializer = storeConfig.dataInitializer();
        dataInitializer.init();
    }
}
