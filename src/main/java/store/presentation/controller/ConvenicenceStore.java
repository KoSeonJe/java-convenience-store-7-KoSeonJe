package store.presentation.controller;

import static store.common.constant.PromotionConstant.NO_OVER_PROMOTION_QUANTITY;

import java.util.List;
import store.common.support.StoreMapper;
import store.common.util.StoreUtils;
import store.domain.Product;
import store.domain.Promotion;
import store.domain.PurchaseInfo;
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
        List<PurchaseInfo> purchaseInfos = requirePurchaseItem();
        checkPromotion(purchaseInfos);

    }

    private void checkPromotion(List<PurchaseInfo> purchaseInfos) {
        purchaseInfos.forEach(purchaseInfo -> {
            Promotion promotion = storeService.findPromotion(purchaseInfo);
            if (promotion == null) {
                return;
            }
            checkAddPromotionQuantity(promotion, purchaseInfo);
            checkOverPromotionQuantity(promotion, purchaseInfo);
            checkMembership(purchaseInfo);
        });
    }

    private void checkMembership(PurchaseInfo purchaseInfo) {
        String answer = applicationView.confirmApplyMembership();
        if (StoreUtils.isAgree(answer)) {
            purchaseInfo.applyMembership();
        }
    }

    private void checkAddPromotionQuantity(Promotion promotion, PurchaseInfo purchaseInfo) {
        if (!storeService.checkAddPromotionQuantity(promotion, purchaseInfo)) {
            return;
        }
        String answer = applicationView.confirmAdditionalItem(purchaseInfo.getName());
        if (StoreUtils.isAgree(answer)) {
            purchaseInfo.addQuantity();
        }
    }

    private void checkOverPromotionQuantity(Promotion promotion, PurchaseInfo purchaseInfo) {
        int quantityDifference = storeService.getQuantityDifference(promotion, purchaseInfo);
        if (quantityDifference == NO_OVER_PROMOTION_QUANTITY) {
            return;
        }
        String answer = applicationView.confirmOriginalPrice(purchaseInfo.getName(), quantityDifference);
        if (StoreUtils.isAgree(answer)) {
            purchaseInfo.changePromotion(quantityDifference);
            return;
        }
        purchaseInfo.deleteNoPromotion(quantityDifference);
    }

    //TODO: 존재하지 않은 상품 입력한 경우, 구매 수량이 재고 수량을 초과한 경우 예외 처리
    private List<PurchaseInfo> requirePurchaseItem() {
        List<Product> products = storeService.getAllProduct();
        applicationView.introduceItem(ProductAllInfo.from(products));
        String inputtedItems = applicationView.inputPurchaseItem();
        return storeMapper.toPurchaseInfo(inputtedItems);
    }
}
