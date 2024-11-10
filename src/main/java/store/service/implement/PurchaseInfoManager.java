package store.service.implement;

import store.domain.PurchaseInfo;
import store.repository.PurchaseInfoRepository;

public class PurchaseInfoManager {

    private final PurchaseInfoRepository purchaseInfoRepository;

    public PurchaseInfoManager(PurchaseInfoRepository purchaseInfoRepository) {
        this.purchaseInfoRepository = purchaseInfoRepository;
    }

    public void save(PurchaseInfo purchaseInfo) {
        purchaseInfoRepository.save(purchaseInfo);
    }

    public PurchaseInfo getRecent() {
        return purchaseInfoRepository.getRecent();
    }
}
