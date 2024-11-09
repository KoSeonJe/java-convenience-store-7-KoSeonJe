package store.service;

import static store.common.constant.PromotionConstant.NO_OVER_PROMOTION_QUANTITY;

import java.util.List;
import store.common.util.NumberUtils;
import store.domain.Product;
import store.domain.ProductGroup;
import store.domain.Promotion;
import store.domain.PurchaseInfo;
import store.service.implement.ProductFinder;
import store.service.implement.PromotionFinder;

public class ConvenienceStoreService implements StoreService {

    private final ProductFinder productFinder;
    private final PromotionFinder promotionFinder;
    private final PromotionChecker promotionChecker;

    public ConvenienceStoreService(ProductFinder productFinder, PromotionFinder promotionFinder,
            PromotionChecker promotionChecker) {
        this.productFinder = productFinder;
        this.promotionFinder = promotionFinder;
        this.promotionChecker = promotionChecker;
    }

    @Override
    public List<Product> getAllProduct() {
        return productFinder.getAllProduct();
    }

    @Override
    public boolean checkAddPromotionQuantity(Promotion promotion, PurchaseInfo purchaseInfo) {
        ProductGroup productGroup = productFinder.findAllByName(purchaseInfo.getName());
        Product promotionProduct = productGroup.findpromotionProduct();
        if (promotionProduct.getQuantity() == purchaseInfo.getOriginQuantity()) {
            return false;
        }
        return promotionChecker.shouldAddProduct(promotion, purchaseInfo.getOriginQuantity());
    }

    @Override
    public Promotion findPromotion(PurchaseInfo purchaseInfo) {
        ProductGroup productGroup = productFinder.findAllByName(purchaseInfo.getName());
        Product promotionProduct = productGroup.findpromotionProduct();
        if(promotionProduct == null) {
            return null;
        }
        return promotionFinder.findByName(promotionProduct.getPromotionName());
    }

    @Override
    public int getQuantityDifference(Promotion promotion, PurchaseInfo purchaseInfo) {
        ProductGroup productGroup = productFinder.findAllByName(purchaseInfo.getName());
        Product promotionProduct = productGroup.findpromotionProduct();
        int difference = promotionProduct.getQuantity() - purchaseInfo.getOriginQuantity();
        if (NumberUtils.isPositive(difference)) {
            return NO_OVER_PROMOTION_QUANTITY;
        }
        //프로모션 상품 /
        int promotionUnit = promotion.getGet() + promotion.getBuy();
        int promotionTarget = promotionProduct.getQuantity() - (promotionProduct.getQuantity() % promotionUnit);
        return purchaseInfo.getOriginQuantity() - promotionTarget;
    }
}
