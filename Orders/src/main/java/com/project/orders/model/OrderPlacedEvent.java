package com.project.orders.model;

import lombok.Data;

@Data
public class OrderPlacedEvent {

    private Long order_id;
    private Long user_id;
    private String email_id;
    private String product_name;

    public OrderPlacedEvent(Long orderId, Long userId, String emailId, String productName, String product_name) {
    }
}
