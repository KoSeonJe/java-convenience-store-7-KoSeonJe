package store.purchase.repository;

import store.purchase.domain.PurchaseInfo;

public interface PurchaseInfoRepository {

    void save(PurchaseInfo purchaseInfo);

    PurchaseInfo getRecent();
}
