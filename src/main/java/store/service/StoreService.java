package store.service;

import java.util.List;
import store.domain.Product;
import store.presentation.dto.RequestItem;

public interface StoreService {

    List<Product> getAllProduct();

    boolean checkPromotionQuantity(RequestItem requestItem);
}
