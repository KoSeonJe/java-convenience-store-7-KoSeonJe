package store.payment.service;

import java.util.ArrayList;
import java.util.List;
import store.payment.domain.PurchaseInfo;
import store.payment.domain.PurchaseItemInfo;
import store.payment.domain.Receipt;
import store.payment.dto.CreateReceiptInfo;
import store.payment.dto.ProductDeductionInfo;
import store.payment.implement.ReceiptFactory;
import store.payment.implement.ReceiptFinder;
import store.product.domain.ProductGroup;
import store.product.service.ProductService;

public class PaymentService {

    private final ProductService productService;
    private final ReceiptFactory receiptFactory;
    private final ReceiptFinder receiptFinder;

    public PaymentService(ProductService productService, ReceiptFactory receiptFactory, ReceiptFinder receiptFinder) {
        this.productService = productService;
        this.receiptFactory = receiptFactory;
        this.receiptFinder = receiptFinder;
    }

    public List<ProductDeductionInfo> process(PurchaseInfo purchaseInfo) {
        List<ProductDeductionInfo> productDeductionInfos = new ArrayList<>();
        purchaseInfo.getPurchaseItemInfos().forEach(purchaseItemInfo -> {
            ProductDeductionInfo productDeductionInfo = deductProduct(purchaseItemInfo);
            productDeductionInfos.add(productDeductionInfo);
        });
        return productDeductionInfos;
    }

    public void createReceipt(CreateReceiptInfo receiptInfo) {
        receiptFactory.create(receiptInfo);
    }

    public Receipt getRecentReceipt() {
        return receiptFinder.getRecent();
    }

    private ProductDeductionInfo deductProduct(PurchaseItemInfo purchaseItemInfo) {
        ProductGroup productGroup = productService.findAllByName(purchaseItemInfo.getName());

        int remainQuantity = productService.deductPromotion(productGroup.findPromotionProduct(),
                purchaseItemInfo.getQuantity());
        productService.deductOrigin(productGroup.findNonPromotionProduct(), remainQuantity);

        int deductPromotionCount = purchaseItemInfo.getQuantity() - remainQuantity;
        return new ProductDeductionInfo(purchaseItemInfo.getName(), deductPromotionCount, remainQuantity);
    }
}
