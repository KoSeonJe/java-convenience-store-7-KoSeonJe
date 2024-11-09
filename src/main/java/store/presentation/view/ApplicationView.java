package store.presentation.view;

import store.presentation.dto.ProductAllInfo;

public interface ApplicationView {

    void introduceItem(ProductAllInfo response);

    String inputPurchaseItem();

    String confirmAdditionalItem(String name);

    String confirmOriginalPrice(String name, int quantityDifference);

    String confirmApplyMembership();
}
