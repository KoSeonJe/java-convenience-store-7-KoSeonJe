package store.common.support;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import fixture.StoreFixture;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.payment.domain.PurchaseItemInfo;
import store.product.domain.Product;
import store.promotion.domain.Promotion;

class StoreMapperTest {

    private StoreMapper storeMapper = StoreFixture.storeMapper();

    @DisplayName("파일 형식이 지켜지고 문자열로 된 Product 리스트를 객체로 변환한다.")
    @Test
    void toProducts() {
        assertSimpleTest(() -> {
            List<String> rawProductInfo = List.of("콜라,1000,10,탄산2+1",  "사이다,1000,8,탄산2+1");
            List<Product> products = storeMapper.toProducts(rawProductInfo);

            assertThat(products).hasSize(2);
            assertThat(products.getFirst().getPrice()).isEqualTo(new BigDecimal(1000));
            assertThat(products.getFirst().getName()).isEqualTo("콜라");
        });
    }

    @Test
    void toPromotions() {
        assertSimpleTest(() -> {
            List<String> rawPromotionInfo = List.of("탄산2+1,2,1,2024-01-01,2024-12-31",  "MD추천상품,1,1,2024-01-01,2024-12-31");
            List<Promotion> promotions = storeMapper.toPromotions(rawPromotionInfo);

            assertThat(promotions).hasSize(2);
            assertThat(promotions.getFirst().getName()).isEqualTo("탄산2+1");
            assertThat(promotions.getFirst().getBuy()).isEqualTo(2);
        });
    }

    @Test
    void toPurchaseInfo() {
        assertSimpleTest(() -> {
            String rawPurchaseItemInfos = "[사이다-1],[콜라-2]";
            List<PurchaseItemInfo> purchaseItemInfos = storeMapper.toPurchaseInfo(rawPurchaseItemInfos);

            assertThat(purchaseItemInfos).hasSize(2);
            assertThat(purchaseItemInfos.getFirst().getName()).isEqualTo("사이다");
            assertThat(purchaseItemInfos.getFirst().getQuantity()).isEqualTo(1);
        });
    }
}
