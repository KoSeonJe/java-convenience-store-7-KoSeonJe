package store.view;

import java.util.List;
import store.domain.Product;

public interface ApplicationView {

    void introduceItem(List<Product> products);

    String inputPurchaseItem();
}
