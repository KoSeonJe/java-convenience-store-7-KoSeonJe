package store.presentation.controller;

import java.util.List;
import store.payment.domain.PurchaseItemInfo;

public interface StoreFront {

    List<PurchaseItemInfo> requirePurchaseItem();

    void checkPromotion(List<PurchaseItemInfo> purchaseItems);

    void processPayment();

    void printReceipt();

    String askContinue();
}
