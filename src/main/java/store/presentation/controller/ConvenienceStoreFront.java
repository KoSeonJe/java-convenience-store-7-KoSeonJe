package store.presentation.controller;

import java.util.List;
import store.purchase.domain.PurchaseInfo;
import store.purchase.domain.PurchaseItemInfo;

public class ConvenienceStoreFront implements StoreFront{

    private final ProductController productController;
    private final PromotionController promotionController;
    private final PurchaseController purchaseController;

    public ConvenienceStoreFront(ProductController productController, PromotionController promotionController,
            PurchaseController purchaseController) {
        this.productController = productController;
        this.promotionController = promotionController;
        this.purchaseController = purchaseController;
    }

    @Override
    public List<PurchaseItemInfo> requirePurchaseItem() {
        return purchaseController.requirePurchaseItem();
    }

    @Override
    public void checkPromotion(List<PurchaseItemInfo> purchaseItemInfos) {
        promotionController.checkAllAddPromotionQuantity(purchaseItemInfos);
        promotionController.checkAllOverPromotionQuantity(purchaseItemInfos);
        boolean isMembership = promotionController.checkMembership();
        purchaseController.savePurchaseInfo(new PurchaseInfo(purchaseItemInfos, isMembership));
    }

    @Override
    public void processPayment() {

    }
}
