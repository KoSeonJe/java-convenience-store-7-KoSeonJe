package store.service.implement;

import java.util.List;
import store.domain.Product;
import store.domain.ProductGroup;

public class ProductManager {
    private final ProductFinder productFinder;

    public ProductManager(ProductFinder productFinder) {
        this.productFinder = productFinder;
    }

    public List<Product> getAllProduct() {
        return productFinder.getAllProduct();
    }

    public ProductGroup findAllByName(String name) {
        return productFinder.findAllByName(name);
    }
}
