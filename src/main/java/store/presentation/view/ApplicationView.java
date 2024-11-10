package store.presentation.view;

import java.util.List;
import store.payment.domain.PurchaseItemInfo;
import store.presentation.dto.ProductAllInfo;

public interface ApplicationView {

    void introduceItem(ProductAllInfo response);

    List<PurchaseItemInfo> inputPurchaseItem();

    String confirmAdditionalItem(String name, int getCount);

    String confirmOriginalPrice(String name, int quantityDifference);

    String confirmApplyMembership();
}
