package store.presentation.view.console;

import java.text.DecimalFormat;
import store.payment.domain.Receipt;
import store.presentation.dto.ProductAllInfo;
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
    private static final String RECEIPT_HEADER = "\n==============W 편의점================";
    private static final String RECEIPT_PRODUCT_HEADER = "상품명\t\t수량\t금액";
    private static final String RECEIPT_PRODUCT_CONTENT = "%s\t\t%d \t%s\n";
    private static final String RECEIPT_PROMOTION_HEADER = "=============증\t정===============";
    private static final String RECEIPT_PROMOTION_CONTENT = "%s\t\t%d\n";
    private static final String RECEIPT_SEPARATOR = "====================================";
    private static final String RECEIPT_DISCOUNT_FINAL_AMOUNT = "총구매액\t\t%d\t%s\n";
    private static final String RECEIPT_DISCOUNT_CONTENT = "%s\t\t\t%s\n";
    private static final String EVENT_DISCOUNT = "행사할인";
    private static final String MEMBERSHIP_DISCOUNT = "멤버십할인";
    private static final String PAYMENT = "내실돈";

    @Override
    public void printWelcomeMessage() {
        println(WELCOME_MESSAGE);
    }

    @Override
    public void printInventory(ProductAllInfo productAllInfo) {
        println(INTRODUCE_INVENTORY_MESSAGE + LINE_SEPARATOR);
        StringBuilder builder = new StringBuilder();
        for (ProductInfo productInfo : productAllInfo.productInfos()) {
            builder.append(ITEM_PREFIX)
                    .append(BLANK).append(productInfo.name())
                    .append(BLANK).append(FOMMATER.format(productInfo.price())).append(PRICE_UNIT);
            appendQuantity(builder, productInfo.quantity());
            appendPromotionName(builder, productInfo.promotionName());
        }
        println(builder.toString());
    }

    @Override
    public void printReceipt(Receipt receipt) {
        println(RECEIPT_HEADER);
        println(RECEIPT_PRODUCT_HEADER);
        printProductContent(receipt);
        println(RECEIPT_PROMOTION_HEADER);
        printPromotionContent(receipt);
        println(RECEIPT_SEPARATOR);
        printAllDiscount(receipt);
    }

    private void printAllDiscount(Receipt receipt) {
        String fomattedFinalAmount = FOMMATER.format(receipt.finalAmount());
        System.out.printf(RECEIPT_DISCOUNT_FINAL_AMOUNT, receipt.getTotalQuantity(), fomattedFinalAmount);

        String fomattedApplyPromotionDiscount = FOMMATER.format(-receipt.applyPromotionDiscount());
        System.out.printf(RECEIPT_DISCOUNT_CONTENT, EVENT_DISCOUNT, fomattedApplyPromotionDiscount);

        String fomattedMembershipDiscount = FOMMATER.format(-receipt.membershipDiscount());
        System.out.printf(RECEIPT_DISCOUNT_CONTENT, MEMBERSHIP_DISCOUNT, fomattedMembershipDiscount);

        String fomattedPaymentPrice = FOMMATER.format(receipt.paymentPrice());
        System.out.printf(RECEIPT_DISCOUNT_CONTENT, PAYMENT, fomattedPaymentPrice);
    }

    private void printPromotionContent(Receipt receipt) {
        receipt.paymentProducts().forEach(paymentProduct -> {
            if (paymentProduct.applyPromotionQuantity() != 0) {
                System.out.printf(RECEIPT_PROMOTION_CONTENT, paymentProduct.name(),
                        paymentProduct.applyPromotionQuantity());
            }
        });
    }

    private void printProductContent(Receipt receipt) {
        receipt.paymentProducts().forEach(paymentProduct -> {
            String fomattedPrice = FOMMATER.format(paymentProduct.totalPrice());
            System.out.printf(RECEIPT_PRODUCT_CONTENT, paymentProduct.name(), paymentProduct.quantity(), fomattedPrice);
        });
    }

    private void appendQuantity(StringBuilder builder, int quantity) {
        if (quantity == 0) {
            builder.append(BLANK).append(NO_EXIST_PRODUCT);
            return;
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
