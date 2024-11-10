package store.payment.service;

import java.util.ArrayList;
import java.util.List;
import store.payment.domain.PurchaseInfo;
import store.payment.domain.PurchaseItemInfo;
import store.payment.domain.Receipt;
import store.payment.dto.CreateReceiptInfo;
import store.payment.dto.ProductDeductionInfo;
import store.payment.implement.ReceiptFactory;
import store.product.domain.ProductGroup;
import store.product.service.ProductService;

public class PaymentService {

    private final ProductService productService;
    private final ReceiptFactory receiptFactory;

    public PaymentService(ProductService productService, ReceiptFactory receiptFactory) {
        this.productService = productService;
        this.receiptFactory = receiptFactory;
    }

    public List<ProductDeductionInfo> process(PurchaseInfo purchaseInfo) {
        List<ProductDeductionInfo> productDeductionInfos = new ArrayList<>();
        purchaseInfo.getPurchaseItemInfos().forEach(purchaseItemInfo -> {
            ProductDeductionInfo productDeductionInfo = deductProduct(purchaseItemInfo);
            productDeductionInfos.add(productDeductionInfo);
        });
        return productDeductionInfos;
    }

    public Receipt createReceipt(CreateReceiptInfo receiptInfo) {
        return receiptFactory.create(receiptInfo);
    }

    public void save(Receipt receipt) {
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
