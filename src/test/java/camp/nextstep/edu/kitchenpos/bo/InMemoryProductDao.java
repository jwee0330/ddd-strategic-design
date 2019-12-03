package camp.nextstep.edu.kitchenpos.bo;

import camp.nextstep.edu.kitchenpos.dao.ProductDao;
import camp.nextstep.edu.kitchenpos.model.Product;

import java.util.*;

public class InMemoryProductDao implements ProductDao {
    private final Map<Long, Product> products = new HashMap<>();

    @Override
    public Product save(final Product entity) {
        products.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }
}
