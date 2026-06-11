package com.project.orders.repository;

import com.project.orders.entity.Orders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository repository;

    @Test
    void saveOrder() {

        Orders order = new Orders();
        order.setProductId(1L);
        order.setUserId(1L);
        order.setQuantity(2);
        order.setTotalPrice(500.0);

        Orders saved = repository.save(order);

        assertNotNull(saved.getOrderId());
    }
}