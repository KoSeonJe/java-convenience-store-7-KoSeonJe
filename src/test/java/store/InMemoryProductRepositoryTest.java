package store;

import static org.assertj.core.api.Assertions.assertThat;

import fixture.StoreFixture;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.Product;
import store.repository.InMemoryProductRepository;

class InMemoryProductRepositoryTest {

    private InMemoryProductRepository repository = StoreFixture.productRepository();

    @BeforeEach
    void setUp() {
        repository.clear();
    }

    @DisplayName("key의 값이 존재하지 않을 경우, 리스트를 새로 생성해 값을 추가한다.")
    @Test
    void saveAllIfAbsent() {
        //given
        String name = "콜라";
        BigDecimal price = BigDecimal.valueOf(1000);
        int quantity = 10;
        String promotionName = "탄산2+1";
        List<Product> products = List.of(Product.create(name, price, quantity, promotionName));

        //when
        repository.saveAll(products);

        //then
        List<Product> findProduct = repository.findByName("콜라");
        assertThat(findProduct).isNotNull();
        assertThat(findProduct).hasSize(1);
        assertThat(findProduct.getFirst().getName()).isEqualTo("콜라");
        assertThat(findProduct.getFirst().getPrice()).isEqualTo(new BigDecimal(1000));
        assertThat(findProduct.getFirst().getQuantity()).isEqualTo(10);
        assertThat(findProduct.getFirst().getPromotionName()).isEqualTo("탄산2+1");
    }

    @DisplayName("key의 값이 존재할 경우, 기존 리스트에 값을 추가한다.")
    @Test
    void saveAllIfExist() {
        //given
        String name = "콜라";
        BigDecimal price = BigDecimal.valueOf(1000);
        int quantity = 10;
        String promotionName = "탄산2+1";
        List<Product> products = List.of(Product.create(name, price, quantity, promotionName));
        repository.saveAll(products);
        List<Product> products2 = List.of(Product.create(name, price, quantity, null));

        //when
        repository.saveAll(products2);

        //then
        List<Product> findProduct = repository.findByName("콜라");
        assertThat(findProduct).isNotNull();
        assertThat(findProduct).hasSize(2);
        assertThat(findProduct.get(1).getName()).isEqualTo("콜라");
        assertThat(findProduct.get(1).getPrice()).isEqualTo(new BigDecimal(1000));
        assertThat(findProduct.get(1).getQuantity()).isEqualTo(10);
        assertThat(findProduct.get(1).getPromotionName()).isNull();
    }
}
