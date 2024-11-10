package store;

import java.util.List;
import store.presentation.controller.StoreFront;
import store.presentation.view.ApplicationView;
import store.purchase.domain.PurchaseItemInfo;

public class ConvenicenceStore implements Store {

    private final ApplicationView applicationView;
    private final StoreFront storeFront;

    public ConvenicenceStore(ApplicationView applicationView, StoreFront storeFront) {
        this.applicationView = applicationView;
        this.storeFront = storeFront;
    }

    @Override
    public void open() {
        List<PurchaseItemInfo> purchaseItemInfos = storeFront.requirePurchaseItem();
        storeFront.checkPromotion(purchaseItemInfos);
        storeFront.processPayment();
    }

//    private void processPayment() {
//        PurchaseInfo purchaseInfo = storeService.getRecentPurchaseInfo();
//        PurchaseHistory purchaseHistory = storeService.processPayment(purchaseInfo);
//    }
}
