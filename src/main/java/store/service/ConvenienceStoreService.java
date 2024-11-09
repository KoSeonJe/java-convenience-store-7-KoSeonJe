package store.service;

import java.util.List;
import store.domain.Product;
import store.domain.ProductGroup;
import store.domain.Promotion;
import store.presentation.dto.RequestItem;
import store.service.implement.ProductFinder;
import store.service.implement.PromotionFinder;

public class ConvenienceStoreService implements StoreService {

    private final ProductFinder productFinder;
    private final PromotionFinder promotionFinder;

    public ConvenienceStoreService(ProductFinder productFinder, PromotionFinder promotionFinder) {
        this.productFinder = productFinder;
        this.promotionFinder = promotionFinder;
    }

    @Override
    public List<Product> getAllProduct() {
        return productFinder.getAllProduct();
    }

    @Override
    public boolean checkPromotionQuantity(RequestItem requestItem) {
        ProductGroup productGroup = productFinder.findAllByName(requestItem.getName());
        Product promotionProduct = productGroup.findpromotionProduct();
        if (promotionProduct == null) {
            return false;
        }
        Promotion promotion = promotionFinder.findByName(promotionProduct.getPromotionName());
        int applyPromotion = promotion.getBuy() + promotion.getGet();
        return promotion.getBuy() == (requestItem.getQuantity() % applyPromotion);
    }
}
