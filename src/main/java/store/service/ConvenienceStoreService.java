package store.service;

import static store.common.constant.PromotionConstant.NO_OVER_PROMOTION_QUANTITY;

import java.util.List;
import store.common.util.NumberUtils;
import store.domain.Product;
import store.domain.ProductGroup;
import store.domain.PurchaseHistory;
import store.domain.PurchaseInfo;
import store.domain.PurchaseItemInfo;
import store.service.implement.ProductManager;
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
    public boolean checkAddPromotionQuantity(Product promotionProduct, PurchaseItemInfo purchaseItemInfo) {
        if (promotionProduct.getQuantity() == purchaseItemInfo.getOriginQuantity()) {
            return false;
        }
        return promotionManager.checkAddProduct(promotionProduct, purchaseItemInfo.getOriginQuantity());
    }

    @Override
    public Product findPromotionProduct(PurchaseItemInfo purchaseItemInfo) {
        ProductGroup productGroup = productManager.findAllByName(purchaseItemInfo.getName());
        return productGroup.findPromotionProduct();
    }

    @Override
    public int getQuantityDifference(Product promotionProduct, PurchaseItemInfo purchaseItemInfo) {
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

    @Override
    public PurchaseInfo getRecentPurchaseInfo() {
        return purchaseInfoManager.getRecent();
    }

    @Override
    public PurchaseHistory processPayment(PurchaseInfo purchaseInfo) {

        return null;
    }
}
