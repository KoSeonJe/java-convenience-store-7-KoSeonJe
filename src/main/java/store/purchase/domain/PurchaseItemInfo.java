package store.purchase.domain;

import store.common.util.StoreUtils;

public class PurchaseItemInfo {

    private String name;

    private int allQuantity;

    private int originQuantity;

    private int promotionQuantity;

    public PurchaseItemInfo(String name, int allQuantity) {
        this.name = name;
        this.allQuantity = allQuantity;
    }

    public String getName() {
        return name;
    }

    public int getAllQuantity() {
        return allQuantity;
    }

    public int getOriginQuantity() {
        return originQuantity;
    }

    public int getPromotionQuantity() {
        return promotionQuantity;
    }

    public void addAllQuantity() {
        allQuantity++;
    }

    public void updateOriginQuantity() {
        this.originQuantity = allQuantity;
    }

    public void updatePromotionQuantity() {
        this.promotionQuantity = allQuantity;
    }

    public void updateQuantityByAnswer(String answer, int quantityDifference) {
        if (StoreUtils.isAgree(answer)) {
            this.originQuantity = quantityDifference;
            this.promotionQuantity = allQuantity - quantityDifference;
            return;
        }
        this.allQuantity -= quantityDifference;
        this.promotionQuantity = allQuantity;
    }
}
