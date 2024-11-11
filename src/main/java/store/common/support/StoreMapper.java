package store.common.support;

import static store.common.constant.StoreConstant.PRODUCT_NAME_INDEX;
import static store.common.constant.StoreConstant.PRODUCT_PRICE_INDEX;
import static store.common.constant.StoreConstant.PRODUCT_PROMOTION_NAME_INDEX;
import static store.common.constant.StoreConstant.PRODUCT_QUANTITY_INDEX;
import static store.common.constant.StoreConstant.PROMOTION_BUY_INDEX;
import static store.common.constant.StoreConstant.PROMOTION_END_DATE_INDEX;
import static store.common.constant.StoreConstant.PROMOTION_GET_INDEX;
import static store.common.constant.StoreConstant.PROMOTION_NAME_INDEX;
import static store.common.constant.StoreConstant.PROMOTION_START_DATE_INDEX;
import static store.common.constant.StoreConstant.REQUEST_ITEM_NAME_INDEX;
import static store.common.constant.StoreConstant.REQUEST_ITEM_QUANTITY_INDEX;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import store.common.exception.InputPurchaseFormatException;
import store.payment.domain.PurchaseItemInfo;
import store.product.domain.Product;
import store.promotion.domain.Promotion;

public class StoreMapper {

    private static final String DATA_SEPARATOR = ",";
    private static final String NULL_MESSAGE = "null";
    private static final String NAME_QUANTITY_SEPARATOR = "-";
    private static final String INPUT_PURCHASE_FORMAT = "^\\[(\\p{L}+\\-\\p{L}+)\\](,\\[(\\p{L}+\\-\\p{L}+)\\])*$";

    public List<Product> toProducts(List<String> rawProducts) {
        List<Product> products = new ArrayList<>();
        rawProducts.forEach(rawProduct -> {
            List<String> fields = List.of(rawProduct.split(DATA_SEPARATOR));
            Product product = creatProduct(fields);
            products.add(product);
        });
        return products;
    }

    public List<Promotion> toPromotions(List<String> rawPromotions) {
        List<Promotion> promotions = new ArrayList<>();
        rawPromotions.forEach(rawPromotion -> {
            List<String> fields = List.of(rawPromotion.split(DATA_SEPARATOR));
            Promotion promotion = createPromotions(fields);
            promotions.add(promotion);
        });
        return promotions;

    }

    public List<PurchaseItemInfo> toPurchaseInfo(String rawRequestItems) {
        if (!rawRequestItems.matches(INPUT_PURCHASE_FORMAT)) {
            throw new InputPurchaseFormatException();
        }
        List<String> splitItems = List.of(rawRequestItems.split(DATA_SEPARATOR));
        return splitItems.stream()
                .map(this::createPurchaseInfo)
                .toList();
    }

    private Product creatProduct(List<String> fields) {
        String name = fields.get(PRODUCT_NAME_INDEX);
        BigDecimal price = new BigDecimal(fields.get(PRODUCT_PRICE_INDEX));
        int quantity = Integer.parseInt(fields.get(PRODUCT_QUANTITY_INDEX));
        String promotionName = fields.get(PRODUCT_PROMOTION_NAME_INDEX);
        if (promotionName.equals(NULL_MESSAGE)) {
            return Product.create(name, price, quantity, null);
        }
        return Product.create(name, price, quantity, promotionName);
    }

    private Promotion createPromotions(List<String> fields) {
        String name = fields.get(PROMOTION_NAME_INDEX);
        int buy = Integer.parseInt(fields.get(PROMOTION_BUY_INDEX));
        int get = Integer.parseInt(fields.get(PROMOTION_GET_INDEX));
        LocalDate startDate = LocalDate.parse(fields.get(PROMOTION_START_DATE_INDEX));
        LocalDate endDate = LocalDate.parse(fields.get(PROMOTION_END_DATE_INDEX));
        return Promotion.create(name, buy, get, startDate, endDate);
    }

    private PurchaseItemInfo createPurchaseInfo(String splitItem) {
        String content = splitItem.substring(1, splitItem.length() - 1);
        String[] itemInfo = content.split(NAME_QUANTITY_SEPARATOR);
        String name = itemInfo[REQUEST_ITEM_NAME_INDEX];
        int quantity = Integer.parseInt(itemInfo[REQUEST_ITEM_QUANTITY_INDEX]);
        return new PurchaseItemInfo(name, quantity);
    }
}
