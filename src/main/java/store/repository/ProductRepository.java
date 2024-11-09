package store.repository;

import java.util.List;
import java.util.Optional;
import store.domain.Product;
import store.domain.ProductGroup;

public interface ProductRepository {

    void saveAll(List<Product> products);

    Optional<ProductGroup> findByName(String name);

    void clear();

    List<ProductGroup> findAll();
}
