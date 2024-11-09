package store.domain;

public class PurchaseInfo {

    private String name;

    private int originQuantity;

    private int promotionQuantity;

    private boolean isMembership;

    public PurchaseInfo(String name, int originQuantity) {
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

    public boolean isMembership() {
        return isMembership;
    }

    public void applyMembership() {
        this.isMembership = true;
    }

    public void addQuantity() {
        originQuantity++;
    }

    public void deleteNoPromotion(int quantityDifference) {
        this.originQuantity -= quantityDifference;
        this.promotionQuantity = this.originQuantity;
        this.originQuantity = 0;
    }

    public void changePromotion(int quantityDifference) {
        this.originQuantity -= quantityDifference;
        this.promotionQuantity = this.originQuantity;
        this.originQuantity = quantityDifference;
    }
}
