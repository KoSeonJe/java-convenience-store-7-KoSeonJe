package store.promotion.repository;

import java.util.List;
import store.promotion.domain.Promotion;

public interface PromotionRepository {

    void saveAll(List<Promotion> promotions);

    Promotion findByName(String name);

    void clear();
}
