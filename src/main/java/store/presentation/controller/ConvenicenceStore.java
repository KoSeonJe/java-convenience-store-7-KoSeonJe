package store.presentation.controller;

import static store.common.constant.PromotionConstant.NO_OVER_PROMOTION_QUANTITY;

import java.util.List;
import store.common.support.StoreMapper;
import store.common.util.StoreUtils;
import store.domain.Product;
import store.domain.PurchaseInfo;
import store.domain.PurchaseItemInfo;
import store.presentation.dto.ProductAllInfo;
import store.presentation.view.ApplicationView;
import store.service.StoreService;

public class ConvenicenceStore implements Store {

    private final ApplicationView applicationView;
    private final StoreService storeService;
    private final StoreMapper storeMapper;

    public ConvenicenceStore(ApplicationView applicationView, StoreService storeService,
            StoreMapper storeMapper) {
        this.applicationView = applicationView;
        this.storeService = storeService;
        this.storeMapper = storeMapper;
    }

    @Override
    public void open() {
        List<PurchaseItemInfo> purchaseItemInfos = requirePurchaseItem();
        checkPromotion(purchaseItemInfos);
        processPayment();

    }

    private void processPayment() {
        PurchaseInfo purchaseInfo = storeService.getRecentPurchaseInfo();
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

    //TODO: 존재하지 않은 상품 입력한 경우, 구매 수량이 재고 수량을 초과한 경우 예외 처리
    private List<PurchaseItemInfo> requirePurchaseItem() {
        List<Product> products = storeService.getAllProduct();
        applicationView.introduceItem(ProductAllInfo.from(products));
        String inputtedItems = applicationView.inputPurchaseItem();
        return storeMapper.toPurchaseInfo(inputtedItems);
    }
}
