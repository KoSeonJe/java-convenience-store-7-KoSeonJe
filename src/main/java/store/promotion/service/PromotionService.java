package store.promotion.service;

import static store.common.constant.PromotionConstant.NO_OVER_PROMOTION_QUANTITY;

import java.time.LocalDateTime;
import store.common.util.NumberUtils;
import store.payment.domain.PurchaseItemInfo;
import store.product.domain.Product;
import store.promotion.domain.Promotion;
import store.promotion.implement.PromotionChecker;
import store.promotion.implement.PromotionFinder;

public class PromotionService {

    private final PromotionFinder promotionFinder;
    private final PromotionChecker promotionChecker;

    public PromotionService(PromotionFinder promotionFinder, PromotionChecker promotionChecker) {
        this.promotionFinder = promotionFinder;
        this.promotionChecker = promotionChecker;
    }

    public Promotion findByName(String promotionName) {
        return promotionFinder.findByName(promotionName);
    }

    public boolean checkAddPromotionQuantity(Product promotionProduct, PurchaseItemInfo purchaseItemInfo) {
        if (promotionProduct.getQuantity() <= purchaseItemInfo.getQuantity()) {
            return false;
        }
        Promotion promotion = promotionFinder.findByName(promotionProduct.getPromotionName());
        return promotionChecker.shouldAddProduct(promotion, purchaseItemInfo.getQuantity());
    }

    public int getQuantityDifference(Product promotionProduct, PurchaseItemInfo purchaseItemInfo) {
        int nonDiscountedQuantity = promotionFinder.findNonDiscountedQuantity(promotionProduct, purchaseItemInfo);
        if (NumberUtils.isNotNegative(nonDiscountedQuantity)) {
            return NO_OVER_PROMOTION_QUANTITY;
        }
        return Math.abs(nonDiscountedQuantity);
    }

    public boolean isDiscountActive(Product promotionProduct, LocalDateTime now) {
        Promotion promotion = promotionFinder.findByName(promotionProduct.getPromotionName());
        return promotion.isActive(now);
    }
}
