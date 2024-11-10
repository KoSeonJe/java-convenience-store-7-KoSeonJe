package store.payment.domain;

import java.util.List;

public class PurchaseInfo {

    private List<PurchaseItemInfo> purchaseItemInfos;
    private boolean isMembership;

    public PurchaseInfo(List<PurchaseItemInfo> purchaseItemInfos, boolean isMembership) {
        this.purchaseItemInfos = purchaseItemInfos;
        this.isMembership = isMembership;
    }

    public List<PurchaseItemInfo> getPurchaseItemInfos() {
        return purchaseItemInfos;
    }

    public boolean isMembership() {
        return isMembership;
    }
}
