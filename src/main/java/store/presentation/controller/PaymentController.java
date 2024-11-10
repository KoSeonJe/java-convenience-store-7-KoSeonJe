package store.presentation.controller;

import java.util.List;
import store.payment.domain.PurchaseInfo;
import store.payment.domain.Receipt;
import store.payment.dto.CreateReceiptInfo;
import store.payment.dto.ProductDeductionInfo;
import store.payment.service.PaymentService;
import store.payment.service.PurchaseInfoService;

public class PaymentController {

    private final PurchaseInfoService purchaseInfoService;
    private final PaymentService paymentService;

    public PaymentController(PurchaseInfoService purchaseInfoService, PaymentService paymentService) {
        this.purchaseInfoService = purchaseInfoService;
        this.paymentService = paymentService;
    }

    public void processPayment() {
        PurchaseInfo purchaseInfo = purchaseInfoService.getRecent();
        List<ProductDeductionInfo> productDeductionInfos = paymentService.process(purchaseInfo);
        CreateReceiptInfo createReceiptInfo = new CreateReceiptInfo(productDeductionInfos, purchaseInfo.isMembership());
        Receipt receipt = paymentService.createReceipt(createReceiptInfo);
        paymentService.save(receipt);
    }
}
