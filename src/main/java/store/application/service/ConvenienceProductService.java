package store.application.service;

import java.util.List;
import store.application.ProductService;
import store.domain.Product;

public class ConvenienceProductService implements ProductService {

    private final ProductFinder productFinder;

    public ConvenienceProductService(ProductFinder productFinder) {
        this.productFinder = productFinder;
    }

    @Override
    public List<Product> getAllProduct() {
        return productFinder.getAllProduct();
    }
}
