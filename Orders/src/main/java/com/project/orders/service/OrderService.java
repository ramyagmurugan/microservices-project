package com.project.orders.service;

import com.project.orders.entity.Orders;
import com.project.orders.feignclient.ProductClient;
import com.project.orders.feignclient.UserClient;
import com.project.orders.model.OrderPlacedEvent;
import com.project.orders.model.ProductResponse;
import com.project.orders.model.UserResponse;
import com.project.orders.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
@Slf4j
@Service
public class OrderService {
    @Autowired
    ProductClient productClient;

    @Autowired
    UserClient userClient;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;


    @CircuitBreaker(name = "Product",fallbackMethod = "fallbackMethod")
    public String placeOrder(Orders orders) {
        log.info("Order received = {}", orders.toString());
        log.info("User Id = {}", orders.getUserId());
        log.info("Product Id = {}", orders.getProductId());
        UserResponse user =userClient.getUser(orders.getUserId());

        if(user == null) {
            throw new RuntimeException("User not found");
        }
        UserResponse userResponse = userClient.getUser(orders.getUserId());
        ProductResponse productResponse = productClient.getProduct(orders.getProductId());
        if(productResponse == null) {
            throw new RuntimeException(
                    "Product not found");
        }
        orders.setTotalPrice(productResponse.getPrice()* orders.getQuantity());
        orderRepository.save(orders);
        OrderPlacedEvent event =
                new OrderPlacedEvent(
                        orders.getOrderId(),
                        orders.getUserId(),
                        userResponse.getEmail_id(),
                        userResponse.getRoles(),
                        productResponse.getProduct_name());
        kafkaProducerService.sendOrderEvent(event);

        return "Order successfully placed for "+ productResponse.getProduct_name();
    }

    public String fallbackMethod(Orders orders,Exception ex) {
        ex.printStackTrace();
        return "Fallback triggered because: " + ex.getMessage();
    }

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Orders> getUserById(Long orderId) {
        return Optional.of(orderRepository.findById(orderId).orElseThrow(() ->
                new RuntimeException("Product not found with id: " + orderId)));
    }
}
