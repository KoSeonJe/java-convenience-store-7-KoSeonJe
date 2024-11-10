package store.presentation.dto;

import java.util.List;
import store.product.domain.Product;

public record ProductAllInfo(
        List<ProductInfo> productInfos
) {

    public static ProductAllInfo from(List<Product> products) {
        return new ProductAllInfo(products.stream()
                .map(ProductInfo::from)
                .toList());
    }
}
