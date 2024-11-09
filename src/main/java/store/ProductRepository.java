package store;

import java.util.List;

public interface ProductRepository {

    void saveAll(List<Product> products);

    List<Product> findByName(String name);

    void clear();
}
