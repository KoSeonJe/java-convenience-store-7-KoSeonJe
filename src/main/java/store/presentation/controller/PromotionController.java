package store.presentation.controller;

import static store.common.constant.PromotionConstant.NO_OVER_PROMOTION_QUANTITY;

import camp.nextstep.edu.missionutils.DateTimes;
import java.util.List;
import store.common.support.Answer;
import store.payment.domain.PurchaseItemInfo;
import store.presentation.view.ApplicationView;
import store.product.domain.Product;
import store.product.service.ProductService;
import store.promotion.domain.Promotion;
import store.promotion.service.PromotionService;

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

    public void checkPromotionQuantity(List<PurchaseItemInfo> purchaseItemInfos) {
        purchaseItemInfos.forEach(purchaseItemInfo -> {
            Product promotionProduct = productService.findPromotionProduct(purchaseItemInfo);
            if (promotionProduct == null || !promotionService.isDiscountActive(promotionProduct, DateTimes.now())) {
                return;
            }
            checkAddPromotionQuantity(promotionProduct, purchaseItemInfo);
            checkOverPromotionQuantity(promotionProduct, purchaseItemInfo);
        });
    }

    public boolean checkMembership() {
        String answer = applicationView.confirmApplyMembership();
        return Answer.isAgree(answer);
    }

    private void checkAddPromotionQuantity(Product promotionProduct, PurchaseItemInfo purchaseItemInfo) {
        if (!promotionService.checkAddPromotionQuantity(promotionProduct, purchaseItemInfo)) {
            return;
        }
        Promotion promotion = promotionService.findByName(promotionProduct.getPromotionName());
        String answer = applicationView.confirmAdditionalItem(purchaseItemInfo.getName(), promotion.getGet());
        if (Answer.isAgree(answer)) {
            purchaseItemInfo.addAllQuantity(promotion.getGet());
        }
    }

    private void checkOverPromotionQuantity(Product promotionProduct, PurchaseItemInfo purchaseItemInfo) {
        int quantityDifference = promotionService.getQuantityDifference(promotionProduct, purchaseItemInfo);
        if (quantityDifference == NO_OVER_PROMOTION_QUANTITY) {
            return;
        }
        String answer = applicationView.confirmOriginalPrice(purchaseItemInfo.getName(), quantityDifference);
        purchaseItemInfo.updateQuantityByAnswer(answer, quantityDifference);
    }
}
