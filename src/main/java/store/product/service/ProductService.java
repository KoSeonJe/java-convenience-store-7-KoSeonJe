package store.product.service;

import java.util.List;
import store.payment.domain.PurchaseItemInfo;
import store.product.domain.Product;
import store.product.domain.ProductGroup;
import store.product.implement.ProductFinder;

public class ProductService {

    private static final int NO_REMAIN_QUANTITY = 0;

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

    public int deductPromotion(Product promotionProduct, int quantity) {
        if (promotionProduct == null) {
            return quantity;
        }
        if (promotionProduct.isEnough(quantity)) {
            promotionProduct.deduct(quantity);
            return NO_REMAIN_QUANTITY;
        }
        int remainQuantity = quantity - promotionProduct.getQuantity();
        promotionProduct.quantityClear();
        return remainQuantity;
    }

    public void deductOrigin(Product nonPromotionProduct, int remainQuantity) {
        if (remainQuantity == NO_REMAIN_QUANTITY || nonPromotionProduct == null) {
            return;
        }
        nonPromotionProduct.deduct(remainQuantity);
    }
}
