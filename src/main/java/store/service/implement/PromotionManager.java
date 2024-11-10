package store.service.implement;

import store.domain.Promotion;
import store.service.PromotionChecker;

public class PromotionManager {

    private final PromotionFinder promotionFinder;
    private final PromotionChecker promotionChecker;

    public PromotionManager(PromotionFinder promotionFinder, PromotionChecker promotionChecker) {
        this.promotionFinder = promotionFinder;
        this.promotionChecker = promotionChecker;
    }

    public boolean shouldAddProduct(Promotion promotion, int originQuantity) {
        return promotionChecker.shouldAddProduct(promotion, originQuantity);
    }

    public Promotion findByName(String promotionName) {
        return promotionFinder.findByName(promotionName);
    }
}
