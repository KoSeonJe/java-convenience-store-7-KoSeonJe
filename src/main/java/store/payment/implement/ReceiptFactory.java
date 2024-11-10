package store.payment.implement;

import java.util.ArrayList;
import java.util.List;
import store.payment.domain.PaymentProduct;
import store.payment.domain.Receipt;
import store.payment.dto.CreateReceiptInfo;
import store.payment.dto.ProductDeductionInfo;
import store.product.domain.Product;
import store.product.domain.ProductGroup;
import store.product.implement.ProductFinder;
import store.promotion.domain.Promotion;
import store.promotion.implement.PromotionFinder;

public class ReceiptFactory {

    private final ProductFinder productFinder;
    private final PromotionFinder promotionFinder;

    public ReceiptFactory(ProductFinder productFinder, PromotionFinder promotionFinder) {
        this.productFinder = productFinder;
        this.promotionFinder = promotionFinder;
    }

    public Receipt create(CreateReceiptInfo receiptInfo) {
        List<PaymentProduct> paymentProducts = extractPaymentProduct(receiptInfo);
    }

    private List<PaymentProduct> extractPaymentProduct(CreateReceiptInfo receiptInfo) {
        List<PaymentProduct> paymentProducts = new ArrayList<>();
        receiptInfo.productDeductionInfos().forEach(productDeductionInfo -> {
            ProductGroup productGroup = productFinder.findAllByName(productDeductionInfo.name());
            String name = productDeductionInfo.name();
            int totalQuantity = productDeductionInfo.getAllQuantity();
            int totalPrice = productGroup.getProductPrice() * productDeductionInfo.getAllQuantity();
            int applyPromotionQuantity = getApplyPromotionQuantity(productGroup, productDeductionInfo);
            paymentProducts.add(new PaymentProduct(name, totalQuantity, totalPrice, applyPromotionQuantity));
        });
        return paymentProducts;
    }

    private int getApplyPromotionQuantity(ProductGroup productGroup, ProductDeductionInfo productDeductionInfo) {
        Product promotionProduct = productGroup.findPromotionProduct();
        Promotion promotion = promotionFinder.findByName(promotionProduct.getPromotionName());
        return productDeductionInfo.getPromotionQuantity(promotion.getBuy() + promotion.getGet());
    }
}
