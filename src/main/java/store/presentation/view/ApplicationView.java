package store.presentation.view;

import store.presentation.dto.GetAllProductResponse;

public interface ApplicationView {

    void introduceItem(GetAllProductResponse response);

    String inputPurchaseItem();
}
