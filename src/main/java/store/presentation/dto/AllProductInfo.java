package store.presentation.dto;

import java.util.List;
import store.domain.Product;

public record AllProductInfo(
        List<ProductInfo> productInfos
) {

    public static AllProductInfo from(List<Product> products) {
        return new AllProductInfo(products.stream()
                .map(ProductInfo::from)
                .toList());
    }
}
