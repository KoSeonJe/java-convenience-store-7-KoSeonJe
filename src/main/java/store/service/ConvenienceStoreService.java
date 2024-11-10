package store.service;

import static store.common.constant.PromotionConstant.NO_OVER_PROMOTION_QUANTITY;

import java.util.List;
import store.common.util.NumberUtils;
import store.domain.Product;
import store.domain.ProductGroup;
import store.domain.Promotion;
import store.domain.PurchaseInfo;
import store.domain.PurchaseItemInfo;
import store.service.implement.ProductFinder;
import store.service.implement.PromotionFinder;
import store.service.implement.PurchaseInfoManager;

public class ConvenienceStoreService implements StoreService {

    private final ProductFinder productFinder;
    private final PromotionFinder promotionFinder;
    private final PromotionChecker promotionChecker;
    private final PurchaseInfoManager purchaseInfoManager;

    public ConvenienceStoreService(ProductFinder productFinder, PromotionFinder promotionFinder,
            PromotionChecker promotionChecker, PurchaseInfoManager purchaseInfoManager) {
        this.productFinder = productFinder;
        this.promotionFinder = promotionFinder;
        this.promotionChecker = promotionChecker;
        this.purchaseInfoManager = purchaseInfoManager;
    }

    @Override
    public List<Product> getAllProduct() {
        return productFinder.getAllProduct();
    }

    @Override
    public boolean checkAddPromotionQuantity(Promotion promotion, PurchaseItemInfo purchaseItemInfo) {
        ProductGroup productGroup = productFinder.findAllByName(purchaseItemInfo.getName());
        Product promotionProduct = productGroup.findpromotionProduct();
        if (promotionProduct.getQuantity() == purchaseItemInfo.getOriginQuantity()) {
            return false;
        }
        return promotionChecker.shouldAddProduct(promotion, purchaseItemInfo.getOriginQuantity());
    }

    @Override
    public Promotion findPromotion(PurchaseItemInfo purchaseItemInfo) {
        ProductGroup productGroup = productFinder.findAllByName(purchaseItemInfo.getName());
        Product promotionProduct = productGroup.findpromotionProduct();
        if(promotionProduct == null) {
            return null;
        }
        return promotionFinder.findByName(promotionProduct.getPromotionName());
    }

    @Override
    public int getQuantityDifference(Promotion promotion, PurchaseItemInfo purchaseItemInfo) {
        ProductGroup productGroup = productFinder.findAllByName(purchaseItemInfo.getName());
        Product promotionProduct = productGroup.findpromotionProduct();
        int difference = promotionProduct.getQuantity() - purchaseItemInfo.getOriginQuantity();
        if (NumberUtils.isPositive(difference)) {
            return NO_OVER_PROMOTION_QUANTITY;
        }
        int promotionUnit = promotion.getGet() + promotion.getBuy();
        int promotionTarget = promotionProduct.getQuantity() - (promotionProduct.getQuantity() % promotionUnit);
        return purchaseItemInfo.getOriginQuantity() - promotionTarget;
    }

    @Override
    public void savePurchaseInfo(PurchaseInfo purchaseInfo) {
        purchaseInfoManager.save(purchaseInfo);
    }
}
