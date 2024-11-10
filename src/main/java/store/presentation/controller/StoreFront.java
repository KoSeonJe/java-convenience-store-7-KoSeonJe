package store.presentation.controller;

import java.util.List;
import store.purchase.domain.PurchaseItemInfo;

public interface StoreFront {

    List<PurchaseItemInfo> requirePurchaseItem();

    void checkPromotion(List<PurchaseItemInfo> purchaseItems);

    void processPayment();
}
