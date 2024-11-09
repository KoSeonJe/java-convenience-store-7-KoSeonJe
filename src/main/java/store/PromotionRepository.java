package store;

import java.util.List;
import java.util.Optional;

public interface PromotionRepository {

    void saveAll(List<Promotion> promotions);

    Optional<Promotion> findByName(String name);

    void clear();
}
