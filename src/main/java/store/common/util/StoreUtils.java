package store.common.util;

import static store.common.constant.PromotionConstant.AGREE_MESSAGE;

import java.util.Objects;

public class StoreUtils {

    public static boolean isAgree(String answer) {
        return Objects.equals(answer, AGREE_MESSAGE);
    }
}
