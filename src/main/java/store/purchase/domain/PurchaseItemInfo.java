package store.purchase.domain;

import store.common.util.StoreUtils;

public class PurchaseItemInfo {

    private String name;

    private int quantity;

    public PurchaseItemInfo(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addAllQuantity(int getCount) {
        quantity = quantity + getCount;
    }

    public void updateQuantityByAnswer(String answer, int quantityDifference) {
        if (StoreUtils.isAgree(answer)) {
            return;
        }
        quantity = quantity - quantityDifference;
    }
}
