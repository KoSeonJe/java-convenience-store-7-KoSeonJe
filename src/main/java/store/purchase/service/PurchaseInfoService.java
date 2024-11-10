package store.purchase.service;

import store.purchase.domain.PurchaseInfo;
import store.purchase.repository.PurchaseInfoRepository;

public class PurchaseInfoService {

    private final PurchaseInfoRepository purchaseInfoRepository;

    public PurchaseInfoService(PurchaseInfoRepository purchaseInfoRepository) {
        this.purchaseInfoRepository = purchaseInfoRepository;
    }

    public void save(PurchaseInfo purchaseInfo) {
        purchaseInfoRepository.save(purchaseInfo);
    }

    public PurchaseInfo getRecent() {
        return purchaseInfoRepository.getRecent();
    }
}
