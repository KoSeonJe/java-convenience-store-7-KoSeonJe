package store.presentation.controller;

import static store.common.constant.PromotionConstant.NO_OVER_PROMOTION_QUANTITY;

import java.util.List;
import store.common.util.StoreUtils;
import store.presentation.view.ApplicationView;
import store.product.domain.Product;
import store.product.service.ProductService;
import store.promotion.service.PromotionService;
import store.purchase.domain.PurchaseItemInfo;

public class PromotionController {

    private final ApplicationView applicationView;
    private final PromotionService promotionService;
    private final ProductService productService;

    public PromotionController(ApplicationView applicationView, PromotionService promotionService,
            ProductService productService) {
        this.applicationView = applicationView;
        this.promotionService = promotionService;
        this.productService = productService;
    }

    public void checkAllAddPromotionQuantity(List<PurchaseItemInfo> purchaseItemInfos) {
        purchaseItemInfos.forEach(purchaseItemInfo -> {
            Product promotionProduct = productService.findPromotionProduct(purchaseItemInfo);
            if (promotionProduct == null) {
                return;
            }
            checkAddPromotionQuantity(promotionProduct, purchaseItemInfo);
        });
    }

    public void checkAllOverPromotionQuantity(List<PurchaseItemInfo> purchaseItemInfos) {
        purchaseItemInfos.forEach(purchaseItemInfo -> {
            Product promotionProduct = productService.findPromotionProduct(purchaseItemInfo);
            if (promotionProduct == null) {
                return;
            }
            checkOverPromotionQuantity(promotionProduct, purchaseItemInfo);
        });
    }

    public boolean checkMembership() {
        String answer = applicationView.confirmApplyMembership();
        return StoreUtils.isAgree(answer);
    }

    private void checkAddPromotionQuantity(Product promotionProduct, PurchaseItemInfo purchaseItemInfo) {
        if (!promotionService.checkAddPromotionQuantity(promotionProduct, purchaseItemInfo)) {
            return;
        }
        String answer = applicationView.confirmAdditionalItem(purchaseItemInfo.getName());
        if (StoreUtils.isAgree(answer)) {
            purchaseItemInfo.addAllQuantity();
        }
    }

    private void checkOverPromotionQuantity(Product promotionProduct, PurchaseItemInfo purchaseItemInfo) {
        int quantityDifference = promotionService.getQuantityDifference(promotionProduct, purchaseItemInfo);
        if (quantityDifference == NO_OVER_PROMOTION_QUANTITY) {
            return;
        }
        String answer = applicationView.confirmOriginalPrice(purchaseItemInfo.getName(), quantityDifference);
        if (StoreUtils.isAgree(answer)) {
            purchaseItemInfo.updateQuantity(quantityDifference);
            return;
        }
        purchaseItemInfo.deleteNoPromotion(quantityDifference);
    }
}
