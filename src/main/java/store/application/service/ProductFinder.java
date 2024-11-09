package store.application.service;

import java.util.ArrayList;
import java.util.List;
import store.domain.Product;
import store.domain.ProductGroup;
import store.repository.ProductRepository;

public class ProductFinder {

    private final ProductRepository productRepository;

    public ProductFinder(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct() {
        List<Product> products = new ArrayList<>();
        List<ProductGroup> productGroups = productRepository.findAll();
        productGroups.forEach(productGroup -> products.addAll(productGroup.getProducts()));
        return products;
    }
}
