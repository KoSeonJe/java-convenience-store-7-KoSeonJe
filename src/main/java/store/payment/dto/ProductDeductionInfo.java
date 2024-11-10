package store.payment.dto;

public record ProductDeductionInfo(
        String name,
        int promotion,
        int origin
) {

    public int getPromotionQuantity(int promotionUnit) {
        return promotion / promotionUnit;
    }

    public int getAllQuantity() {
        return origin + promotion;
    }
}
