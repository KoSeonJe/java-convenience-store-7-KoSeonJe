package store.service.implement;

import store.domain.Promotion;
import store.repository.PromotionRepository;

public class PromotionFinder {

    private final PromotionRepository promotionRepository;

    public PromotionFinder(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public Promotion findByName(String promotionName) {
        return promotionRepository.findByName(promotionName).get();
    }
}
