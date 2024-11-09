package store.service;

import java.util.List;
import store.domain.Product;
import store.domain.Promotion;
import store.domain.PurchaseInfo;

public interface StoreService {

    List<Product> getAllProduct();

    boolean checkAddPromotionQuantity(Promotion promotion, PurchaseInfo purchaseInfo);

    Promotion findPromotion(PurchaseInfo purchaseInfo);

    int getQuantityDifference(Promotion promotion, PurchaseInfo purchaseInfo);
}
