package store.product.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import store.product.domain.Product;
import store.product.domain.ProductGroup;

public class InMemoryProductRepository implements ProductRepository {

    private static final InMemoryProductRepository INSTANCE = new InMemoryProductRepository();

    private final Map<String, ProductGroup> repository = new ConcurrentHashMap<>();

    private InMemoryProductRepository() {

    }

    public static InMemoryProductRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void saveAll(List<Product> products) {
        products.forEach(
                product -> repository.computeIfAbsent(product.getName(), key -> new ProductGroup(new ArrayList<>())).add(product)
        );
    }

    @Override
    public ProductGroup findByName(String name) {
        return repository.get(name);
    }

    @Override
    public List<ProductGroup> findAll() {
        Set<String> productNames = repository.keySet();
        return productNames.stream()
                .map(repository::get)
                .toList();
    }

    @Override
    public void clear() {
        repository.clear();
    }
}
