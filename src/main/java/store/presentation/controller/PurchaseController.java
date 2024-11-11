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
    private final ApplicationView applicationView;
    private final PurchaseInfoService purchaseInfoService;

    public PurchaseController(ProductService productService, ApplicationView applicationView,
            PurchaseInfoService purchaseInfoService) {
        this.productService = productService;
        this.applicationView = applicationView;
        this.purchaseInfoService = purchaseInfoService;
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
