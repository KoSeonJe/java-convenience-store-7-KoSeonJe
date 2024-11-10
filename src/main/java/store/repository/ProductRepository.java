package store.repository;

import java.util.List;
import store.domain.Product;
import store.domain.ProductGroup;

public interface ProductRepository {

    void saveAll(List<Product> products);

    ProductGroup findByName(String name);

    void clear();

    List<ProductGroup> findAll();
}
