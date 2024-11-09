package store.domain;

import java.util.Collections;
import java.util.List;

public class ProductGroup {

    private final List<Product> products;

    public ProductGroup(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public void add(Product product) {
        products.add(product);
    }
}