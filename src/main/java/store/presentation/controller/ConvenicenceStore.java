package store.presentation.controller;

import static store.common.constant.PromotionConstant.NO_OVER_PROMOTION_QUANTITY;

import java.util.List;
import store.common.util.StoreUtils;
import store.domain.Product;
import store.domain.PurchaseHistory;
import store.domain.PurchaseInfo;
import store.domain.PurchaseItemInfo;
import store.presentation.dto.ProductAllInfo;
import store.presentation.view.ApplicationView;
import store.service.StoreService;
import store.service.StoreValidator;

public class ConvenicenceStore implements Store {

    private final ApplicationView applicationView;
    private final StoreService storeService;
    private final StoreValidator storeValidator;

    public ConvenicenceStore(ApplicationView applicationView, StoreService storeService, StoreValidator storeValidator) {
        this.applicationView = applicationView;
        this.storeService = storeService;
        this.storeValidator = storeValidator;
    }

    @Override
    public void open() {
        List<PurchaseItemInfo> purchaseItemInfos = requirePurchaseItem();
        checkPromotion(purchaseItemInfos);
        processPayment();

    }

    private void processPayment() {
        PurchaseInfo purchaseInfo = storeService.getRecentPurchaseInfo();
        PurchaseHistory purchaseHistory = storeService.processPayment(purchaseInfo);
    }

    private void checkPromotion(List<PurchaseItemInfo> purchaseItemInfos) {
        purchaseItemInfos.forEach(purchaseItemInfo -> {
            Product promotionProduct = storeService.findPromotionProduct(purchaseItemInfo);
            if (promotionProduct == null) {
                return;
            }
            checkAddPromotionQuantity(promotionProduct, purchaseItemInfo);
            checkOverPromotionQuantity(promotionProduct, purchaseItemInfo);
        });
        boolean isMembership = checkMembership();
        storeService.savePurchaseInfo(new PurchaseInfo(purchaseItemInfos, isMembership));
    }

    private boolean checkMembership() {
        String answer = applicationView.confirmApplyMembership();
        return StoreUtils.isAgree(answer);
    }

    private void checkAddPromotionQuantity(Product promotionProduct, PurchaseItemInfo purchaseItemInfo) {
        if (!storeService.checkAddPromotionQuantity(promotionProduct, purchaseItemInfo)) {
            return;
        }
        String answer = applicationView.confirmAdditionalItem(purchaseItemInfo.getName());
        if (StoreUtils.isAgree(answer)) {
            purchaseItemInfo.addQuantity();
        }
    }

    private void checkOverPromotionQuantity(Product promotionProduct, PurchaseItemInfo purchaseItemInfo) {
        int quantityDifference = storeService.getQuantityDifference(promotionProduct, purchaseItemInfo);
        if (quantityDifference == NO_OVER_PROMOTION_QUANTITY) {
            return;
        }
        String answer = applicationView.confirmOriginalPrice(purchaseItemInfo.getName(), quantityDifference);
        if (StoreUtils.isAgree(answer)) {
            purchaseItemInfo.updatePromotionQuantity(quantityDifference);
            return;
        }
        purchaseItemInfo.deleteNoPromotion(quantityDifference);
    }

    private List<PurchaseItemInfo> requirePurchaseItem() {
        List<Product> products = storeService.getAllProduct();
        applicationView.introduceItem(ProductAllInfo.from(products));
        List<PurchaseItemInfo> purchaseItemInfos = applicationView.inputPurchaseItem();
        storeValidator.enoughQuantity(purchaseItemInfos);
        storeValidator.existProduct(purchaseItemInfos);
        return purchaseItemInfos;
    }
}
