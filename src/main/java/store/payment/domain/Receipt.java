package store.payment.domain;

import java.util.List;

public record Receipt(
        List<PaymentProduct> paymentProducts,
        int finalAmount,
        int applyPromotionDiscount,
        int membershipDiscount,
        int paymentPrice
) {

    public int getTotalQuantity() {
        return paymentProducts.stream()
                .mapToInt(PaymentProduct::quantity)
                .sum();
    }
}
