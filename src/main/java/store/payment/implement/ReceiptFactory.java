package store.payment.implement;

import static store.common.constant.PromotionConstant.NOT_EXIST_PROMOTION;

import camp.nextstep.edu.missionutils.DateTimes;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import store.payment.domain.PaymentProduct;
import store.payment.domain.Receipt;
import store.payment.dto.CreateReceiptInfo;
import store.payment.dto.ProductDeductionInfo;
import store.payment.repository.ReceiptRepository;
import store.product.domain.Product;
import store.product.domain.ProductGroup;
import store.product.implement.ProductFinder;
import store.promotion.domain.Promotion;
import store.promotion.implement.PromotionFinder;

public class ReceiptFactory {

    private static final int NONE_DISCOUNT = 0;
    private static final float MEMBERSHIP_DISCOUNT_RATE = 0.3f;
    private static final int DECIMAL_PLACE = 0;
    private static final int MEMBERSHIP_MAX_DISCOUNT = 8000;

    private final ProductFinder productFinder;
    private final PromotionFinder promotionFinder;
    private final ReceiptRepository receiptRepository;

    public ReceiptFactory(ProductFinder productFinder, PromotionFinder promotionFinder,
            ReceiptRepository receiptRepository) {
        this.productFinder = productFinder;
        this.promotionFinder = promotionFinder;
        this.receiptRepository = receiptRepository;
    }

    public void create(CreateReceiptInfo receiptInfo) {
        List<PaymentProduct> paymentProducts = extractPaymentProduct(receiptInfo.productDeductionInfos());
        int finalAmount = getFinalPrice(paymentProducts);
        int applyPromotionDiscount = getApplyPromotionDiscount(paymentProducts);
        int membershipDiscount = getMembershipDiscount(receiptInfo);
        int paymentPrice = finalAmount - applyPromotionDiscount - membershipDiscount;
        Receipt receipt = new Receipt(paymentProducts, finalAmount, applyPromotionDiscount, membershipDiscount, paymentPrice);
        receiptRepository.save(receipt);
    }

    private int getMembershipDiscount(CreateReceiptInfo receiptInfo) {
        if (!receiptInfo.isMembership()) {
            return NONE_DISCOUNT;
        }
        int totalOriginPrice = receiptInfo.productDeductionInfos().stream()
                .mapToInt(productDeductionInfo -> {
                    ProductGroup productGroup = productFinder.findAllByName(productDeductionInfo.name());
                    int productPrice = productGroup.getProductPrice();
                    int originQuantity = productDeductionInfo.getNonPromotionQuantity(getPromotionUnit(productGroup));
                    return productPrice * originQuantity;
                }).sum();
        return applyMembership(totalOriginPrice);
    }

    private int applyMembership(int totalOriginPrice) {
        BigDecimal price = new BigDecimal(totalOriginPrice); // 예: "10000"
        BigDecimal discountRate = new BigDecimal(MEMBERSHIP_DISCOUNT_RATE); // 30% 할인율
        price = price.multiply(discountRate).setScale(DECIMAL_PLACE, RoundingMode.HALF_UP);
        if (price.intValue() > MEMBERSHIP_MAX_DISCOUNT) {
            return MEMBERSHIP_MAX_DISCOUNT;
        }
        return price.intValue();
    }

    private int getApplyPromotionDiscount(List<PaymentProduct> paymentProducts) {
        return paymentProducts.stream()
                .mapToInt(PaymentProduct::getApplyPromotionPrice)
                .sum();
    }

    private int getFinalPrice(List<PaymentProduct> paymentProducts) {
        return paymentProducts.stream()
                .mapToInt(PaymentProduct::totalPrice)
                .sum();
    }

    private List<PaymentProduct> extractPaymentProduct(List<ProductDeductionInfo> productDeductionInfos) {
        List<PaymentProduct> paymentProducts = new ArrayList<>();
        productDeductionInfos.forEach(productDeductionInfo -> {
            ProductGroup productGroup = productFinder.findAllByName(productDeductionInfo.name());
            String name = productDeductionInfo.name();
            int totalQuantity = productDeductionInfo.getAllQuantity();
            int totalPrice = productGroup.getProductPrice() * productDeductionInfo.getAllQuantity();
            int applyPromotionQuantity = productDeductionInfo.getPromotionQuantity(getPromotionUnit(productGroup));
            paymentProducts.add(new PaymentProduct(name, totalQuantity, totalPrice, applyPromotionQuantity));
        });
        return paymentProducts;
    }

    private int getPromotionUnit(ProductGroup productGroup) {
        Product promotionProduct = productGroup.findPromotionProduct();
        if (promotionProduct == null) {
            return NOT_EXIST_PROMOTION;
        }
        Promotion promotion = promotionFinder.findByName(promotionProduct.getPromotionName());
        if (!promotion.isActive(DateTimes.now())) {
            return NOT_EXIST_PROMOTION;
        }
        return promotion.getBuy() + promotion.getGet();
    }
}
