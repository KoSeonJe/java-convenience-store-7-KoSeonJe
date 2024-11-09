package store.domain;

import static store.common.constant.StoreConstant.PRODUCT_NAME_INDEX;
import static store.common.constant.StoreConstant.PRODUCT_PRICE_INDEX;
import static store.common.constant.StoreConstant.PRODUCT_PROMOTION_NAME_INDEX;
import static store.common.constant.StoreConstant.PRODUCT_QUANTITY_INDEX;

import java.math.BigDecimal;

public class Product {

    private String name;

    private BigDecimal price;

    private int quantity;

    private String promotionName;

    public Product(String name, BigDecimal price, int quantity, String promotionName) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotionName = promotionName;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public static Product create(String[] rawFields) {
        return new Product(
                rawFields[PRODUCT_NAME_INDEX],
                new BigDecimal(rawFields[PRODUCT_PRICE_INDEX]),
                Integer.parseInt(rawFields[PRODUCT_QUANTITY_INDEX]),
                rawFields[PRODUCT_PROMOTION_NAME_INDEX]
        );
    }
}
