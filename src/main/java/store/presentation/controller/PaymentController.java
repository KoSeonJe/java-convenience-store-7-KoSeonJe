package store.presentation.controller;

import java.util.List;
import store.payment.domain.PurchaseInfo;
import store.payment.domain.Receipt;
import store.payment.dto.CreateReceiptInfo;
import store.payment.dto.ProductDeductionInfo;
import store.payment.service.PaymentService;
import store.payment.service.PurchaseInfoService;
import store.presentation.view.ApplicationView;

public class PaymentController {

    private final PurchaseInfoService purchaseInfoService;
    private final PaymentService paymentService;
    private final ApplicationView applicationView;

    public PaymentController(PurchaseInfoService purchaseInfoService, PaymentService paymentService,
            ApplicationView applicationView) {
        this.purchaseInfoService = purchaseInfoService;
        this.paymentService = paymentService;
        this.applicationView = applicationView;
    }

    public void processPayment() {
        PurchaseInfo purchaseInfo = purchaseInfoService.getRecent();
        List<ProductDeductionInfo> productDeductionInfos = paymentService.process(purchaseInfo);
        paymentService.createReceipt(new CreateReceiptInfo(productDeductionInfos, purchaseInfo.isMembership()));
    }

    public void printReceipt() {
        Receipt receipt = paymentService.getRecentReceipt();
        applicationView.printReceipt(receipt);
    }
}
