package store.domain;

import java.math.BigDecimal;

public class Product {

    private String name;

    private BigDecimal price;

    private int quantity;

    private String promotionName;

    private Product(String name, BigDecimal price, int quantity, String promotionName) {
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

    public static Product create(String name, BigDecimal price, int quantity, String promotionName) {
        return new Product(name, price, quantity, promotionName);
    }
}
