package store.repository;

import java.util.List;
import store.domain.Promotion;

public interface PromotionRepository {

    void saveAll(List<Promotion> promotions);

    Promotion findByName(String name);

    void clear();
}
