package store.common.validate;

import static store.common.constant.ExceptionMessage.NO_ENOUGH_PRODUCT_MESSAGE;
import static store.common.constant.ExceptionMessage.NO_EXIST_PRODUCT_MESSAGE;

import java.util.List;
import store.product.domain.ProductGroup;
import store.payment.domain.PurchaseItemInfo;
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
                throw new IllegalArgumentException(NO_ENOUGH_PRODUCT_MESSAGE);
            }
        });
    }

    public void existProduct(List<PurchaseItemInfo> purchaseItemInfos) {
        purchaseItemInfos.forEach(purchaseItemInfo -> {
            String name = purchaseItemInfo.getName();
            ProductGroup productGroup = productFinder.findAllByName(name);
            if(productGroup == null) {
                throw new IllegalArgumentException(NO_EXIST_PRODUCT_MESSAGE);
            }
        });
    }
}
