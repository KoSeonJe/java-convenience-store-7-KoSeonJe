package store.presentation.view.console;

import java.text.DecimalFormat;
import store.presentation.dto.GetAllProductResponse;
import store.presentation.dto.ProductInfo;
import store.presentation.view.OutputView;

public class OutputConsoleView implements OutputView {

    private static final DecimalFormat FOMMATER = new DecimalFormat("#,###");
    private static final String WELCOME_MESSAGE = "안녕하세요. W편의점입니다.";
    private static final String ITEM_PREFIX = "-";
    private static final String BLANK = " ";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String INTRODUCE_INVENTORY_MESSAGE = "현재 보유하고 있는 상품입니다.";
    private static final String PRICE_UNIT = "원";
    private static final String QUANTITY_UNIT = "개";
    private static final String NO_EXIST_PRODUCT = "재고 없음";

    @Override
    public void printWelcomeMessage() {
        println(WELCOME_MESSAGE);
    }

    @Override
    public void printInventory(GetAllProductResponse getAllProductResponse) {
        println(INTRODUCE_INVENTORY_MESSAGE + LINE_SEPARATOR);
        StringBuilder builder = new StringBuilder();
        for (ProductInfo productInfo : getAllProductResponse.productInfos()) {
            builder.append(ITEM_PREFIX)
                    .append(BLANK).append(productInfo.name())
                    .append(BLANK).append(FOMMATER.format(productInfo.price())).append(PRICE_UNIT);
            appendQuantity(builder, productInfo.quantity());
            appendPromotionName(builder, productInfo.promotionName());
        }
        println(builder.toString());
    }

    private void appendQuantity(StringBuilder builder, int quantity) {
        if (quantity == 0) {
            builder.append(BLANK).append(NO_EXIST_PRODUCT);
        }
        builder.append(BLANK).append(quantity).append(QUANTITY_UNIT);
    }

    private void appendPromotionName(StringBuilder builder, String promotionName) {
        if (promotionName == null) {
            builder.append(LINE_SEPARATOR);
            return;
        }
        builder.append(BLANK).append(promotionName).append(LINE_SEPARATOR);
    }

    private void println(String message) {
        System.out.println(message);
    }
}
