package store.presentation.controller;

import store.product.domain.Product;
import store.product.domain.ProductGroup;
import store.product.service.ProductService;
import store.purchase.domain.PurchaseItemInfo;

public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public Product findPromotionProduct(PurchaseItemInfo purchaseItemInfo) {
        ProductGroup productGroup = productService.findAllByName(purchaseItemInfo.getName());
        return productGroup.findPromotionProduct();
    }
}
