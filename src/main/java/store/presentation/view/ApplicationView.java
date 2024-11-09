package store.presentation.view;

import store.presentation.dto.AllProductInfo;

public interface ApplicationView {

    void introduceItem(AllProductInfo response);

    String inputPurchaseItem();
}
