package store.service;

import static store.common.constant.PromotionConstant.NO_OVER_PROMOTION_QUANTITY;

import java.util.List;
import store.common.util.NumberUtils;
import store.domain.Product;
import store.domain.ProductGroup;
import store.domain.Promotion;
import store.domain.PurchaseInfo;
import store.domain.PurchaseItemInfo;
import store.service.implement.PromotionManager;
import store.service.implement.PurchaseInfoManager;

public class ConvenienceStoreService implements StoreService {

    private final ProductManager productManager;
    private final PromotionManager promotionManager;
    private final PurchaseInfoManager purchaseInfoManager;

    public ConvenienceStoreService(ProductManager productManager, PromotionManager promotionManager,
            PurchaseInfoManager purchaseInfoManager) {
        this.productManager = productManager;
        this.promotionManager = promotionManager;
        this.purchaseInfoManager = purchaseInfoManager;
    }

    @Override
    public List<Product> getAllProduct() {
        return productManager.getAllProduct();
    }

    @Override
    public boolean checkAddPromotionQuantity(Promotion promotion, PurchaseItemInfo purchaseItemInfo) {
        ProductGroup productGroup = productManager.findAllByName(purchaseItemInfo.getName());
        Product promotionProduct = productGroup.findpromotionProduct();
        if (promotionProduct.getQuantity() == purchaseItemInfo.getOriginQuantity()) {
            return false;
        }
        return promotionManager.checkAddProduct(promotion, purchaseItemInfo.getOriginQuantity());
    }

    @Override
    public Promotion findPromotion(PurchaseItemInfo purchaseItemInfo) {
        ProductGroup productGroup = productManager.findAllByName(purchaseItemInfo.getName());
        Product promotionProduct = productGroup.findpromotionProduct();
        if(promotionProduct == null) {
            return null;
        }
        return promotionManager.findByName(promotionProduct.getPromotionName());
    }

    @Override
    public int getQuantityDifference(Promotion promotion, PurchaseItemInfo purchaseItemInfo) {
        ProductGroup productGroup = productManager.findAllByName(purchaseItemInfo.getName());
        Product promotionProduct = productGroup.findpromotionProduct();
        int nonDiscountedQuantity = promotionManager.getNonDiscountedQuantity(promotionProduct, purchaseItemInfo);
        if (!NumberUtils.isPositive(nonDiscountedQuantity)) {
            return NO_OVER_PROMOTION_QUANTITY;
        }
        return nonDiscountedQuantity;
    }

    @Override
    public void savePurchaseInfo(PurchaseInfo purchaseInfo) {
        purchaseInfoManager.save(purchaseInfo);
    }
}
