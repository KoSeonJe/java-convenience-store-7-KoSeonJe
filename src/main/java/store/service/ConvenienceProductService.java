package store.service;

import java.util.List;
import store.service.implement.ProductFinder;
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
