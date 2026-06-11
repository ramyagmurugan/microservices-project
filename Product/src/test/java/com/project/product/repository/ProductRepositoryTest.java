package com.project.product.repository;

import com.project.product.entity.Products;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository repository;

    @Test
    void saveProduct() {

        Products product = new Products();
        product.setProduct_name("Laptop");
        product.setPrice(65000.0);

        Products saved = repository.save(product);

        assertNotNull(saved.getProduct_id());
    }
}