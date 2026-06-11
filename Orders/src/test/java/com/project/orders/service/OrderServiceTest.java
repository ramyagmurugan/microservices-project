package com.project.orders.service;

import com.project.orders.entity.Orders;
import com.project.orders.feignclient.ProductClient;
import com.project.orders.feignclient.UserClient;
import com.project.orders.model.ProductResponse;
import com.project.orders.model.UserResponse;
import com.project.orders.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private ProductClient productClient;

    @Mock
    private UserClient userClient;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private KafkaProducerService kafkaProducerService;

    @InjectMocks
    private OrderService orderService;

    @Test
    void placeOrder_Success() {

        Orders order = new Orders();
        order.setOrderId(1L);
        order.setProductId(101L);
        order.setUserId(201L);
        order.setQuantity(2);

        UserResponse user = new UserResponse();
        user.setEmail_id("test@gmail.com");
        user.setRoles("USER");

        ProductResponse product = new ProductResponse();
        product.setProduct_name("Laptop");
        product.setPrice(50000.0);

        when(userClient.getUser(201L)).thenReturn(user);
        when(productClient.getProduct(101L)).thenReturn(product);

        String response = orderService.placeOrder(order);

        assertEquals(
                "Order successfully placed for Laptop",
                response);

        verify(orderRepository, times(1))
                .save(order);

        verify(kafkaProducerService, times(1))
                .sendOrderEvent(any());
    }

    @Test
    void placeOrder_UserNotFound() {

        Orders order = new Orders();
        order.setUserId(1L);

        when(userClient.getUser(1L))
                .thenReturn(null);

        RuntimeException exception =
                assertThrows(RuntimeException.class,
                        () -> orderService.placeOrder(order));

        assertEquals("User not found",
                exception.getMessage());
    }

    @Test
    void getAllOrders() {

        when(orderRepository.findAll())
                .thenReturn(Arrays.asList(
                        new Orders(),
                        new Orders()));

        assertEquals(2,
                orderService.getAllOrders().size());
    }

    @Test
    void getOrderById() {

        Orders order = new Orders();
        order.setOrderId(1L);

        when(orderRepository.findById(1L))
                .thenReturn(Optional.of(order));

        Optional<Orders> result =
                orderService.getUserById(1L);

        assertTrue(result.isPresent());
    }
}