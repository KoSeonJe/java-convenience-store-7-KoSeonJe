package store.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import store.domain.Promotion;

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
    public Optional<Promotion> findByName(String name) {
        return Optional.ofNullable(repository.get(name));
    }

    @Override
    public void clear() {
        repository.clear();
    }
}
