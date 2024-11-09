package store.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import store.domain.Product;

public class InMemoryProductRepository implements ProductRepository {

    private static final InMemoryProductRepository INSTANCE = new InMemoryProductRepository();

    private final Map<String, List<Product>> repository = new ConcurrentHashMap<>();

    private InMemoryProductRepository() {

    }

    public static InMemoryProductRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void saveAll(List<Product> products) {
        products.forEach(
                product -> repository.computeIfAbsent(product.getName(), key -> new ArrayList<>()).add(product)
        );
    }

    @Override
    public List<Product> findByName(String name) {
        return repository.get(name);
    }

    @Override
    public void clear() {
        repository.clear();
    }
}
