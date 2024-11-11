package store.payment.domain;

import static store.common.constant.PromotionConstant.NOT_EXIST_PROMOTION;

public record PaymentProduct(
        String name,
        int quantity,
        int totalPrice,
        int applyPromotionQuantity
) {

    public int getApplyPromotionPrice() {
        if (applyPromotionQuantity == NOT_EXIST_PROMOTION) {
            return NOT_EXIST_PROMOTION;
        }
        int price = totalPrice / quantity;
        return price * applyPromotionQuantity;
    }

}
