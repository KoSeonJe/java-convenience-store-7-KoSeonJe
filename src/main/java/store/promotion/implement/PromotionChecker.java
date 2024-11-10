package store.promotion.implement;

import store.promotion.domain.Promotion;

public class PromotionChecker {

    public boolean shouldAddProduct(Promotion promotion, int requestQuantity) {
        int applyPromotion = promotion.getBuy() + promotion.getGet();
        return promotion.getBuy() == (requestQuantity % applyPromotion);
    }
}
