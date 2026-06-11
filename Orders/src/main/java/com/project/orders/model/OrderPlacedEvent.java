package com.project.orders.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class OrderPlacedEvent {

    private Long order_id;
    private Long user_id;
    private String email_id;
    private String product_name;

}
