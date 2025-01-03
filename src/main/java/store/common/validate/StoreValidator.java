package store.common.validate;

import java.util.List;
import store.common.exception.ResourceInsufficientException;
import store.common.exception.ResourceNotFoundException;
import store.payment.domain.PurchaseItemInfo;
import store.product.domain.ProductGroup;
import store.product.implement.ProductFinder;

public class StoreValidator {

    private final ProductFinder productFinder;

    public StoreValidator(ProductFinder productFinder) {
        this.productFinder = productFinder;
    }

    public void enoughQuantity(List<PurchaseItemInfo> purchaseItemInfos) {
        purchaseItemInfos.forEach(purchaseItemInfo -> {
            String name = purchaseItemInfo.getName();
            ProductGroup productGroup = productFinder.findAllByName(name);
            if (!productGroup.isEnoughProducts(purchaseItemInfo.getQuantity())) {
                throw new ResourceInsufficientException();
            }
        });
    }

    public void existProduct(List<PurchaseItemInfo> purchaseItemInfos) {
        purchaseItemInfos.forEach(purchaseItemInfo -> {
            String name = purchaseItemInfo.getName();
            ProductGroup productGroup = productFinder.findAllByName(name);
            if(productGroup == null) {
                throw new ResourceNotFoundException();
            }
        });
    }
}
