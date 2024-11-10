package store.service;

import static store.common.constant.ExceptionMessage.NO_ENOUGH_PRODUCT_MESSAGE;

import java.util.List;
import store.domain.ProductGroup;
import store.domain.PurchaseItemInfo;
import store.repository.ProductRepository;

public class StoreValidator {

    private final ProductRepository productRepository;

    public StoreValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void enoughQuantity(List<PurchaseItemInfo> purchaseItemInfos) {
        purchaseItemInfos.forEach(purchaseItemInfo -> {
            String name = purchaseItemInfo.getName();
            ProductGroup productGroup = productRepository.findByName(name);
            if (!productGroup.isEnoughProducts(purchaseItemInfo.getOriginQuantity())) {
                throw new IllegalArgumentException(NO_ENOUGH_PRODUCT_MESSAGE);
            }
        });
    }

    public void existProduct(List<PurchaseItemInfo> purchaseItemInfos) {
        purchaseItemInfos.forEach(purchaseItemInfo -> {
            String name = purchaseItemInfo.getName();
            ProductGroup productGroup = productRepository.findByName(name);
            if(productGroup == null) {
                throw new IllegalArgumentException(NO_ENOUGH_PRODUCT_MESSAGE);
            }
        });
    }
}
