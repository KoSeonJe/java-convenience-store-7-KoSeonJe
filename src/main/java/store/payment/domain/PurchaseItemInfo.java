package store.payment.domain;

import store.common.support.Answer;

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
        if (Answer.isAgree(answer)) {
            return;
        }
        quantity = quantity - quantityDifference;
    }
}
