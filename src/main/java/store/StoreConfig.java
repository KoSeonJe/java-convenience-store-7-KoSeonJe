package store;

public final class StoreConfig {

    private static final StoreConfig INSTANCE = new StoreConfig();

    private StoreConfig() {

    }

    public static StoreConfig getInstance() {
        return INSTANCE;
    }

    public DataInitializer dataInitializer() {
        return new FileDataInitializer(new FileLoader());
    }
}
