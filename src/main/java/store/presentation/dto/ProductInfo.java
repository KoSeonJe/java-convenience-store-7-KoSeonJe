package store.presentation.dto;

import java.math.BigDecimal;
import store.domain.Product;

public record ProductInfo(
        String name,

        BigDecimal price,

        int quantity,

        String promotionName
) {

    public static ProductInfo from(Product product) {
        return new ProductInfo(product.getName(), product.getPrice(), product.getQuantity(), product.getPromotionName());
    }
}
