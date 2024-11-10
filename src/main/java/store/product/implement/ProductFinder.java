package store.product.implement;

import java.util.ArrayList;
import java.util.List;
import store.product.domain.Product;
import store.product.domain.ProductGroup;
import store.product.repository.ProductRepository;

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

    public ProductGroup findAllByName(String name) {
        return productRepository.findByName(name);
    }
}
