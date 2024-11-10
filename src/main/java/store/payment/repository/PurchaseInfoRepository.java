package store.payment.repository;

import store.payment.domain.PurchaseInfo;

public interface PurchaseInfoRepository {

    void save(PurchaseInfo purchaseInfo);

    PurchaseInfo getRecent();
}
