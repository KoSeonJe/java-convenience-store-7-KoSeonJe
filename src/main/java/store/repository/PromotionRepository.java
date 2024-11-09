package store.repository;

import java.util.List;
import java.util.Optional;
import store.domain.Promotion;

public interface PromotionRepository {

    void saveAll(List<Promotion> promotions);

    Optional<Promotion> findByName(String name);

    void clear();
}
