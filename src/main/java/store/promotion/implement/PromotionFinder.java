package store.promotion.implement;

import store.product.domain.Product;
import store.promotion.domain.Promotion;
import store.promotion.repository.PromotionRepository;
import store.purchase.domain.PurchaseItemInfo;

public class PromotionFinder {

    private final PromotionRepository promotionRepository;

    public PromotionFinder(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public Promotion findByName(String promotionName) {
        return promotionRepository.findByName(promotionName);
    }

    public int findNonDiscountedQuantity(Product promotionProduct, PurchaseItemInfo purchaseItemInfo) {
        Promotion promotion = findByName(promotionProduct.getPromotionName());
        int promotionUnit = promotion.getGet() + promotion.getBuy();
        int promotionTarget = promotionProduct.getQuantity() - (promotionProduct.getQuantity() % promotionUnit);
        return promotionTarget - purchaseItemInfo.getAllQuantity();
    }
}
