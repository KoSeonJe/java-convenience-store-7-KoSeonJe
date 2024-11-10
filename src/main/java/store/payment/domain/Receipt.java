package store.payment.domain;

import java.util.List;

public record Receipt(
        List<PaymentProduct> paymentProducts,
        int totalAmount,
        int eventDiscount,
        int membershipDiscount,
        int finalAmount
) {
}
