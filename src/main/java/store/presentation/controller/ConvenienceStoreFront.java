package store.presentation.controller;

import java.util.List;
import store.payment.domain.PurchaseInfo;
import store.payment.domain.PurchaseItemInfo;

public class ConvenienceStoreFront implements StoreFront{

    private final PromotionController promotionController;
    private final PurchaseController purchaseController;
    private final PaymentController paymentController;

    public ConvenienceStoreFront(PromotionController promotionController,
            PurchaseController purchaseController, PaymentController paymentController) {
        this.promotionController = promotionController;
        this.purchaseController = purchaseController;
        this.paymentController = paymentController;
    }

    @Override
    public List<PurchaseItemInfo> requirePurchaseItem() {
        return purchaseController.requirePurchaseItem();
    }

    @Override
    public void checkPromotion(List<PurchaseItemInfo> purchaseItemInfos) {
        promotionController.checkAllOverPromotionQuantity(purchaseItemInfos);
        promotionController.checkAllAddPromotionQuantity(purchaseItemInfos);
        boolean isMembership = promotionController.checkMembership();
        purchaseController.savePurchaseInfo(new PurchaseInfo(purchaseItemInfos, isMembership));
    }

    @Override
    public void processPayment() {
        paymentController.processPayment();
    }

    @Override
    public void printReceipt() {
        paymentController.printReceipt();
    }
}
