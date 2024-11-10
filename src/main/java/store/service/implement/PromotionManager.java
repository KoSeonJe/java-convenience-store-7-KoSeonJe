package store.service.implement;

import store.domain.Product;
import store.domain.Promotion;
import store.domain.PurchaseItemInfo;
import store.service.PromotionChecker;

public class PromotionManager {

    private final PromotionFinder promotionFinder;
    private final PromotionChecker promotionChecker;

    public PromotionManager(PromotionFinder promotionFinder, PromotionChecker promotionChecker) {
        this.promotionFinder = promotionFinder;
        this.promotionChecker = promotionChecker;
    }

    public boolean checkAddProduct(Product promotionProduct, int originQuantity) {
        Promotion promotion = promotionFinder.findByName(promotionProduct.getPromotionName());
        return promotionChecker.shouldAddProduct(promotion, originQuantity);
    }

    public Promotion findByName(String promotionName) {
        return promotionFinder.findByName(promotionName);
    }

    public int getNonDiscountedQuantity(Product promotionProduct, PurchaseItemInfo purchaseItemInfo) {
        Promotion promotion = promotionFinder.findByName(promotionProduct.getPromotionName());
        int promotionUnit = promotion.getGet() + promotion.getBuy();
        int promotionTarget = promotionProduct.getQuantity() - (promotionProduct.getQuantity() % promotionUnit);
        return purchaseItemInfo.getOriginQuantity() - promotionTarget;
    }
}
