package store.common.util;

import static store.common.constant.PromotionConstant.AGREE_MESSAGE;

import java.util.Objects;

public class StoreUtils {

    public static boolean isAgree(String answer) {
        if (Objects.equals(answer, AGREE_MESSAGE)) {
            return true;
        }
        return false;
    }
}
