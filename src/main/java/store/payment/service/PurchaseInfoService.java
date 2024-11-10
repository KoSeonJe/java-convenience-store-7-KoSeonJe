package store.payment.service;

import store.payment.domain.PurchaseInfo;
import store.payment.repository.PurchaseInfoRepository;

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
