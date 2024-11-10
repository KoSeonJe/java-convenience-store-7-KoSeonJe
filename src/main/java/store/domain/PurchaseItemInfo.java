package store.domain;

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

    public void deleteNoPromotion(int quantityDifference) {
        this.allQuantity -= quantityDifference;
        this.originQuantity = 0;
        this.promotionQuantity = allQuantity;
    }

    public void updateQuantity(int quantityDifference) {
        this.originQuantity = quantityDifference;
        this.promotionQuantity = allQuantity - quantityDifference;
    }
}
