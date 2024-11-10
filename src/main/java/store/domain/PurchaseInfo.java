package store.domain;

import java.util.List;

public class PurchaseInfo {

    private List<PurchaseItemInfo> purchaseItemInfos;
    private boolean isMembership;

    public PurchaseInfo(List<PurchaseItemInfo> purchaseItemInfos, boolean isMembership) {
        this.purchaseItemInfos = purchaseItemInfos;
        this.isMembership = isMembership;
    }
}
