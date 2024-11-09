package store.repository;

import java.util.List;
import store.domain.Product;

public interface ProductRepository {

    void saveAll(List<Product> products);

    List<Product> findByName(String name);

    void clear();
}
