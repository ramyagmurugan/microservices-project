package com.project.orders.model;

import lombok.Data;

@Data
public class ProductResponse {
    private Long product_id;
    private String product_name;
    private Double price;
}
