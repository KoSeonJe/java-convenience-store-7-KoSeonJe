package store.presentation.controller;

import java.util.List;
import store.presentation.dto.ProductAllInfo;
import store.presentation.view.ApplicationView;
import store.product.domain.Product;
import store.product.service.ProductService;
import store.purchase.domain.PurchaseInfo;
import store.purchase.domain.PurchaseItemInfo;
import store.purchase.service.PurchaseInfoService;
import store.common.validate.StoreValidator;

public class PurchaseController {

    private final ProductService productService;
    private final ApplicationView applicationView;
    private final StoreValidator storeValidator;
    private final PurchaseInfoService purchaseInfoService;

    public PurchaseController(ProductService productService, ApplicationView applicationView,
            StoreValidator storeValidator, PurchaseInfoService purchaseInfoService) {
        this.productService = productService;
        this.applicationView = applicationView;
        this.storeValidator = storeValidator;
        this.purchaseInfoService = purchaseInfoService;
    }

    public List<PurchaseItemInfo> requirePurchaseItem() {
        List<Product> products = productService.getAllProduct();
        applicationView.introduceItem(ProductAllInfo.from(products));
        List<PurchaseItemInfo> purchaseItemInfos = applicationView.inputPurchaseItem();
        storeValidator.enoughQuantity(purchaseItemInfos);
        storeValidator.existProduct(purchaseItemInfos);
        return purchaseItemInfos;
    }

    public void savePurchaseInfo(PurchaseInfo purchaseInfo) {
        purchaseInfoService.save(purchaseInfo);
    }
}
