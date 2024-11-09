package store.application.service;

import java.util.List;
import store.application.StoreService;
import store.domain.Product;

public class ConvenienceStoreService implements StoreService {

    private final ConvenienceStoreFinder finder;

    public ConvenienceStoreService(ConvenienceStoreFinder finder) {
        this.finder = finder;
    }

    @Override
    public List<Product> getAllProduct() {
        return finder.getAllProduct();
    }
}
