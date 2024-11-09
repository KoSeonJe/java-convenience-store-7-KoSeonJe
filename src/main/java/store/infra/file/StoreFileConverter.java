package store.infra.file;

import java.util.ArrayList;
import java.util.List;
import store.domain.Product;
import store.domain.Promotion;

public class StoreFileConverter {

    private static final String FIELD_SEPARATOR = ",";

    public List<Product> toProducts(List<String> rawProducts) {
        List<Product> products = new ArrayList<>();
        rawProducts.forEach(rawProduct -> {
            String[] fields = rawProduct.split(FIELD_SEPARATOR);
            Product product = Product.create(fields);
            products.add(product);
        });
        return products;
    }

    public List<Promotion> toPromotions(List<String> rawPromotions) {
        List<Promotion> promotions = new ArrayList<>();
        rawPromotions.forEach(rawPromotion -> {
            String[] fields = rawPromotion.split(FIELD_SEPARATOR);
            Promotion promotion = Promotion.create(fields);
            promotions.add(promotion);
        });
        return promotions;

    }
}
