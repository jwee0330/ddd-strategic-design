package camp.nextstep.edu.kitchenpos.bo;

import camp.nextstep.edu.kitchenpos.dao.ProductDao;
import camp.nextstep.edu.kitchenpos.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProductBoTest {
    private ProductDao productDao = new InMemoryProductDao();
//    @Mock
//    private ProductDao productDao;
//    @InjectMocks
    private ProductBo bo;
    private Product product;
    private List<Product> products;

    @BeforeEach
    void dummyProductSetUp() {
        bo = new ProductBo(productDao);
    }

    @DisplayName("가격이 없는 새로운 제품을 추가하기")
    @Test
    void createNewProductWithNullPrice() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> bo.create(product));
    }

    @DisplayName("가격이 0원 미만인 새로운 제품을 추가하기")
    @Test
    void createNewProductWithNegativePrice() {
        product.setPrice(BigDecimal.valueOf(-10));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> bo.create(product));
    }

    @DisplayName("가격이 0원인 새로운 제품을 추가하기")
    @Test
    void createNewProductWithZeroPrice() {
        product.setPrice(BigDecimal.valueOf(0));

        bo.create(product);

        assertThat(product.getPrice()).isEqualTo(BigDecimal.ZERO);
    }

    @DisplayName("제품 목록을 출력하기")
    @Test
    void getProductList() {
//        given(productDao.findAll()).willReturn(products);
        final Product product1 = new Product();
        product1.setId(1L);
        product1.setName("꿀닭강정");
        product1.setPrice(BigDecimal.valueOf(19000));

        productDao.save(product1);

        List<Product> products = bo.list();

        assertThat(products).containsExactly(product1);
    }
}
