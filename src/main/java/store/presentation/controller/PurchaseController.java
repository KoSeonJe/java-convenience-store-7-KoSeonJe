package store.presentation.controller;

import java.util.List;
import store.payment.domain.PurchaseInfo;
import store.payment.domain.PurchaseItemInfo;
import store.payment.service.PurchaseInfoService;
import store.presentation.dto.ProductAllInfo;
import store.presentation.view.ApplicationView;
import store.product.domain.Product;
import store.product.service.ProductService;

public class PurchaseController {

    private final ProductService productService;
    private final PurchaseInfoService purchaseInfoService;
    private final ApplicationView applicationView;

    public PurchaseController(ProductService productService, PurchaseInfoService purchaseInfoService,
            ApplicationView applicationView) {
        this.productService = productService;
        this.purchaseInfoService = purchaseInfoService;
        this.applicationView = applicationView;
    }

    public List<PurchaseItemInfo> requirePurchaseItem() {
        List<Product> products = productService.getAllProduct();
        applicationView.introduceItem(ProductAllInfo.from(products));
        return applicationView.inputPurchaseItem();
    }

    public void savePurchaseInfo(PurchaseInfo purchaseInfo) {
        purchaseInfoService.save(purchaseInfo);
    }
}
