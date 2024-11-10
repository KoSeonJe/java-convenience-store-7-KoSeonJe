package store.product.service;

import java.util.List;
import store.product.domain.Product;
import store.product.domain.ProductGroup;
import store.product.implement.ProductFinder;
import store.payment.domain.PurchaseItemInfo;

public class ProductService {
    private final ProductFinder productFinder;

    public ProductService(ProductFinder productFinder) {
        this.productFinder = productFinder;
    }

    public List<Product> getAllProduct() {
        return productFinder.getAllProduct();
    }

    public ProductGroup findAllByName(String name) {
        return productFinder.findAllByName(name);
    }

    public Product findPromotionProduct(PurchaseItemInfo purchaseItemInfo) {
        ProductGroup productGroup = productFinder.findAllByName(purchaseItemInfo.getName());
        return productGroup.findPromotionProduct();
    }
}
