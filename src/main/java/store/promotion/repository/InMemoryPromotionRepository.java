package store.promotion.repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import store.promotion.domain.Promotion;

public class InMemoryPromotionRepository implements PromotionRepository {

    private static final InMemoryPromotionRepository INSTANCE = new InMemoryPromotionRepository();

    private final Map<String, Promotion> repository = new ConcurrentHashMap<>();

    private InMemoryPromotionRepository() {
    }

    public static InMemoryPromotionRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void saveAll(List<Promotion> promotions) {
        promotions.forEach(promotion -> repository.put(promotion.getName(), promotion));
    }

    @Override
    public Promotion findByName(String name) {
        return repository.get(name);
    }

    @Override
    public void clear() {
        repository.clear();
    }
}
