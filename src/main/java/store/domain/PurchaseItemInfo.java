package store.domain;

public class PurchaseItemInfo {

    private String name;

    private int originQuantity;

    private int promotionQuantity;

    public PurchaseItemInfo(String name, int originQuantity) {
        this.name = name;
        this.originQuantity = originQuantity;
    }

    public String getName() {
        return name;
    }

    public int getOriginQuantity() {
        return originQuantity;
    }

    public int getPromotionQuantity() {
        return promotionQuantity;
    }

    public void addQuantity() {
        originQuantity++;
    }

    public void deleteNoPromotion(int quantityDifference) {
        this.originQuantity -= quantityDifference;
        this.promotionQuantity = this.originQuantity;
        this.originQuantity = 0;
    }

    public void updatePromotionQuantity(int quantityDifference) {
        this.originQuantity -= quantityDifference;
        this.promotionQuantity = this.originQuantity;
        this.originQuantity = quantityDifference;
    }
}
