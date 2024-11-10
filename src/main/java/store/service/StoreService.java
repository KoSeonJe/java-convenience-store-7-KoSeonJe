package store.service;

import java.util.List;
import store.domain.Product;
import store.domain.Promotion;
import store.domain.PurchaseInfo;
import store.domain.PurchaseItemInfo;

public interface StoreService {

    List<Product> getAllProduct();

    boolean checkAddPromotionQuantity(Promotion promotion, PurchaseItemInfo purchaseItemInfo);

    Promotion findPromotion(PurchaseItemInfo purchaseItemInfo);

    int getQuantityDifference(Promotion promotion, PurchaseItemInfo purchaseItemInfo);

    void savePurchaseInfo(PurchaseInfo purchaseInfo);
}
