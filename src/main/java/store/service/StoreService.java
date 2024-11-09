package store.service;

import java.util.List;
import store.domain.Product;
import store.domain.Promotion;
import store.presentation.dto.RequestItem;

public interface StoreService {

    List<Product> getAllProduct();

    boolean checkAddPromotionQuantity(Promotion promotion, RequestItem requestItem);

    boolean checkOverPromotionQuantity(Promotion promotion, RequestItem requestItem);

    Promotion findPromotion(RequestItem requestItem);

    int getQuantityDifference(Promotion promotion, RequestItem requestItem);
}
