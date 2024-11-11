package store.presentation.controller;

import java.util.List;
import store.payment.domain.PurchaseInfo;
import store.payment.domain.PurchaseItemInfo;
import store.presentation.view.ApplicationView;

public class ConvenienceStoreFront implements StoreFront{

    private final PromotionController promotionController;
    private final PurchaseController purchaseController;
    private final PaymentController paymentController;
    private final ApplicationView applicationView;

    public ConvenienceStoreFront(PromotionController promotionController,
            PurchaseController purchaseController, PaymentController paymentController, ApplicationView applicationView) {
        this.promotionController = promotionController;
        this.purchaseController = purchaseController;
        this.paymentController = paymentController;
        this.applicationView = applicationView;
    }

    @Override
    public List<PurchaseItemInfo> requirePurchaseItem() {
        return purchaseController.requirePurchaseItem();
    }

    @Override
    public void checkPromotion(List<PurchaseItemInfo> purchaseItemInfos) {
        promotionController.checkPromotionQuantity(purchaseItemInfos);
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

    @Override
    public String askContinue() {
        return applicationView.askContinue();
    }
}
