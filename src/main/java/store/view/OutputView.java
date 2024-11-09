package store.view;

import java.util.List;
import store.domain.Product;

public interface OutputView {

    void printWelcomeMessage();

    void printInventory(List<Product> products);
}
