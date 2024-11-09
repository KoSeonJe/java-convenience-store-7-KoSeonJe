package store.presentation.dto;

import java.util.List;
import store.domain.Product;

public record GetAllProductResponse(
        List<ProductInfo> productInfos
) {

    public static GetAllProductResponse from(List<Product> products) {
        return new GetAllProductResponse(products.stream()
                .map(ProductInfo::from)
                .toList());
    }
}
