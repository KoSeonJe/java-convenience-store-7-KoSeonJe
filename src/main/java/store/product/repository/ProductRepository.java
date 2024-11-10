package store.product.repository;

import java.util.List;
import store.product.domain.Product;
import store.product.domain.ProductGroup;

public interface ProductRepository {

    void saveAll(List<Product> products);

    ProductGroup findByName(String name);

    void clear();

    List<ProductGroup> findAll();
}
