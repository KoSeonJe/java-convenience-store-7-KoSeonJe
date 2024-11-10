package store.product.domain;

import java.util.Collections;
import java.util.List;
import store.common.util.NumberUtils;

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

    public Product findPromotionProduct() {
        return products.stream()
                .filter(product -> product.getPromotionName() != null)
                .findFirst()
                .orElse(null);
    }

    public Product findNonPromotionProduct() {
        return products.stream()
                .filter(product -> product.getPromotionName() == null)
                .findFirst()
                .orElse(null);
    }

    public boolean isEnoughProducts(int quantity) {
        int totalQuantity = products.stream()
                .mapToInt(Product::getQuantity)
                .sum();
        return NumberUtils.isNotNegative(totalQuantity - quantity);
    }
}
