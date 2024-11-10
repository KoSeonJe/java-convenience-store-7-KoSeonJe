package store.service.implement;

import store.domain.Promotion;

public class PromotionChecker {

    public boolean shouldAddProduct(Promotion promotion, int requestQuantity) {
        int applyPromotion = promotion.getBuy() + promotion.getGet();
        return promotion.getBuy() == (requestQuantity % applyPromotion);
    }
}
