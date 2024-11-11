package store;

import java.util.List;
import store.common.util.StoreUtils;
import store.payment.domain.PurchaseItemInfo;
import store.presentation.controller.StoreFront;

public class ConvenicenceStore implements Store {

    private final StoreFront storeFront;

    public ConvenicenceStore(StoreFront storeFront) {
        this.storeFront = storeFront;
    }

    @Override
    public void open() {
        String answer;
        do {
            List<PurchaseItemInfo> purchaseItemInfos = storeFront.requirePurchaseItem();
            storeFront.checkPromotion(purchaseItemInfos);
            storeFront.processPayment();
            storeFront.printReceipt();
            answer = storeFront.askContinue();
        } while (StoreUtils.isAgree(answer));

    }
}
