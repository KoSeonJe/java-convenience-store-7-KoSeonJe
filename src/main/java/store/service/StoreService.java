package store.service;

import java.util.List;
import store.domain.Product;
import store.domain.PurchaseHistory;
import store.domain.PurchaseInfo;
import store.domain.PurchaseItemInfo;

public interface StoreService {

    List<Product> getAllProduct();

    boolean checkAddPromotionQuantity(Product promotionProduct, PurchaseItemInfo purchaseItemInfo);

    Product findPromotionProduct(PurchaseItemInfo purchaseItemInfo);

    int getQuantityDifference(Product promotionProduct, PurchaseItemInfo purchaseItemInfo);

    void savePurchaseInfo(PurchaseInfo purchaseInfo);

    PurchaseInfo getRecentPurchaseInfo();

    PurchaseHistory processPayment(PurchaseInfo purchaseInfo);
}
