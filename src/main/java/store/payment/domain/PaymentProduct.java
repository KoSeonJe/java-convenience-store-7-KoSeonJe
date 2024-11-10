package store.payment.domain;

public record PaymentProduct(
        String name,
        int quantity,
        int totalPrice,
        int applyPromotionQuantity
) {

}
