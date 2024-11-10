package store.payment.dto;

import static store.common.constant.PromotionConstant.NOT_EXIST_PROMOTION;

public record ProductDeductionInfo(
        String name,
        int promotion,
        int origin
) {

    public int getPromotionQuantity(int promotionUnit) {
        if (promotionUnit == NOT_EXIST_PROMOTION) {
            return 0;
        }
        return promotion / promotionUnit;
    }

    public int getNonPromotionQuantity(int promotionUnit) {
        if (promotionUnit == NOT_EXIST_PROMOTION) {
            return origin;
        }
        int remainQuantity = promotion % promotionUnit;
        return remainQuantity + origin;
    }

    public int getAllQuantity() {
        return origin + promotion;
    }
}
