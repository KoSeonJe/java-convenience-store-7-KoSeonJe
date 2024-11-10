package store.payment.domain;

public record PaymentProduct(
        String name,
        int quantity,
        int totalPrice,
        int applyPromotionQuantity
) {

    public int getApplyPromotionPrice() {
        int price = totalPrice / quantity;
        return price * applyPromotionQuantity;
    }

}
